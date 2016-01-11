package netcracker.app.wf.back.hibernate;


import org.hibernate.Query;

import java.util.List;

public class HibernateUtils {

    @SuppressWarnings("unchecked")
    public static synchronized <T> List<T> cast(Query query) {
        return query.list();
    }
}
