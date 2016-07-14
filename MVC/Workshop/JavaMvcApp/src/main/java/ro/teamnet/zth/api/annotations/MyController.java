package ro.teamnet.zth.api.annotations;

import java.lang.annotation.*;


    @Target({ElementType.TYPE})
    @Retention(RetentionPolicy.RUNTIME) //pentru a fi disponibila cand ruleaza aplicatia
    @Documented

    public @interface MyController {
        String urlPath();
    }


