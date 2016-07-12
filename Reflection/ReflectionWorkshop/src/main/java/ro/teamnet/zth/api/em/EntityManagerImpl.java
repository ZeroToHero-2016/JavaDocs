package ro.teamnet.zth.api.em;

import ro.teamnet.zth.api.database.DBManager;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

import static ro.teamnet.zth.api.database.DBManager.*;
import static ro.teamnet.zth.api.em.EntityUtils.castFromSqlType;

public class EntityManagerImpl implements EntityManager{
    public <T> T findById(Class<T> entityClass, Long id) {
        Connection newConnection = null;
        try {
            newConnection = DBManager.getConnection();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (DBManager.checkConnection(newConnection)) {
            String tableName = EntityUtils.getTableName(entityClass);
            List<ColumnInfo> columns = (ArrayList) EntityUtils.getColumns(entityClass);
            Condition condition = new Condition();
            for (ColumnInfo ci : columns) {
                if (ci.isId()) {
                    condition.setColumnName(ci.getDbName());
                    condition.setValue(id);
                }
            }
            QueryBuilder findByIdQuery = new QueryBuilder();
            findByIdQuery.setTableName(tableName);
            findByIdQuery.setQueryType(QueryType.SELECT);
            findByIdQuery.addQueryColumns(columns);
            findByIdQuery.addCondition(condition);
            T instance  = null;
            try {
                instance = entityClass.newInstance();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            try (Statement stmt = newConnection.createStatement()){
                ResultSet findByIdQueryResult = stmt.executeQuery(findByIdQuery.createQuery());
                if (findByIdQueryResult.next()) {
                    for (ColumnInfo column : columns) {
                        try {
                            Field f = entityClass.getDeclaredField(column.getColumnName());
                            f.setAccessible(true);
                            f.set(instance, castFromSqlType(findByIdQueryResult.getObject(column.getDbName()), f.getType()));
                        } catch (NoSuchFieldException e) {
                            e.printStackTrace();
                            System.out.println("no such field exception!\n");
                            return null;
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        }
                    }
                    return instance;
                }
            }
            catch (Exception e) {
                e.printStackTrace();
                System.out.println("Error: unable to create statement!");
                System.exit(1);
                return null;
            }
        }
        return null;
    }

    @Override
    public Long getNextIdVal(String tableName, String columnIdName) {
        Connection connection = null;
        try {
            connection = DBManager.getConnection();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String max_id = null;
        Statement statement = null;
        String query = "SELECT MAX("+ columnIdName +") AS MAX_ID FROM "+ tableName;
        try {
            statement = connection.createStatement();
            ResultSet RS =  statement.executeQuery(query);
            while (RS.next()) {
                max_id = RS.getString("MAX_ID");
            }
            long result = Long.valueOf(max_id).longValue();
            return result+1;
        } catch (SQLException e ) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public <T> Object insert(T entity) {
        Connection connection = null;
        try {
            connection = DBManager.getConnection();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String tableName = EntityUtils.getTableName(entity.getClass());
        ArrayList<ColumnInfo> columnInfoArrayList = (ArrayList<ColumnInfo>) EntityUtils.getColumns(entity.getClass());
        Long id = null;
        for(ColumnInfo columnInfo : columnInfoArrayList){
            if(columnInfo.isId()){
                id = getNextIdVal(tableName,columnInfo.getDbName());
                columnInfo.setValue(id);
            }
            else{
                try {
                    Field field =  entity.getClass().getDeclaredField(columnInfo.getColumnName());
                    field.setAccessible(true);
                    columnInfo.setValue(field.get(entity));
                } catch (NoSuchFieldException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        QueryBuilder queryBuilder = new QueryBuilder();
        queryBuilder = queryBuilder.setTableName(tableName).addQueryColumns(columnInfoArrayList).setQueryType(QueryType.INSERT);
        String query = queryBuilder.createQuery();
        System.out.println(query);
        try (Statement statement = connection.createStatement()){
            statement.execute(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return findById(entity.getClass(),id);
    }

    @Override
    public <T> List<T> findAll(Class<T> entityClass) {
        List<T> myList = new ArrayList<T>();
        try {
            Connection conn = DBManager.getConnection();

            String tableName = EntityUtils.getTableName(entityClass);
            List<ColumnInfo> columns = EntityUtils.getColumns(entityClass);
            QueryBuilder query = new QueryBuilder();
            query.setTableName(tableName);
            query.addQueryColumns(columns);
            query.setQueryType(QueryType.SELECT);
            String SQL = query.createQuery();

            Statement st = conn.createStatement();
            ResultSet results = st.executeQuery(SQL);


            while (results.next()){
                T entitate = entityClass.newInstance();

                for (ColumnInfo column : columns){
                    String colName = column.getColumnName();
                    Field f = entityClass.getDeclaredField(colName);
                    f.setAccessible(true);

                    f.set(entitate, castFromSqlType(results.getObject(column.getDbName()),f.getType()));


                }
                myList.add(entitate);

            }


        } catch (Exception e) {
            e.printStackTrace();
        }

        return myList;
    }

    @Override
    public void deleteById(Object entity) {
        Connection connection = null;
        try {
            connection = DBManager.getConnection();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String tableName = EntityUtils.getTableName(entity.getClass());
        ArrayList<ColumnInfo> columnInfoArrayList = (ArrayList<ColumnInfo>) EntityUtils.getColumns(entity.getClass());
        long id;
        Condition con = new Condition();
        for (ColumnInfo columnInfo : columnInfoArrayList) {
            try {
                Field field = entity.getClass().getDeclaredField(columnInfo.getColumnName());
                field.setAccessible(true);
                columnInfo.setValue(field.get(entity));
            } catch (Exception e) {
                e.printStackTrace();
            }

            if (columnInfo.isId()) {
                id = (long) columnInfo.getValue();
                con.setColumnName(columnInfo.getDbName());
                con.setValue(id);
                //   columnInfo.setValue(id);
            }
        }
        QueryBuilder queryBuilder = new QueryBuilder();
        queryBuilder = queryBuilder.setTableName(tableName).addQueryColumns(columnInfoArrayList).setQueryType(QueryType.DELETE).addCondition(con);
        String query = queryBuilder.createQuery();
        System.out.println(query);
        try (Statement statement = connection.createStatement()) {
            statement.execute(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}


