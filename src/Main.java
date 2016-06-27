import com.King.CommandWorker;
import com.King.MainInformation;
import com.King.Server;
import com.King.dao.GameBD;
import com.King.dao.GameConfigBD;
import com.King.dao.GamePlayersBD;
import com.King.module.cameraWorker.GamesThreads;
import com.King.module.monkeyWorker.MonitoringThread;
import com.King.module.monkeyWorker.PlayersThread;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.hibernate.cfg.Configuration;
import org.hibernate.collection.internal.PersistentSet;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import java.sql.Timestamp;
import java.util.*;

/**
 * Created by wed on 07.02.16.
 */
public class Main {
    public static void main(final String[] args) throws Exception {
        MainInformation.Initialize().Load();
        MonitoringThread.Go();
        PlayersThread.Go();
        new GamesThreads().start();
        new CommandWorker().start();
        Server.Start();
    }
}
