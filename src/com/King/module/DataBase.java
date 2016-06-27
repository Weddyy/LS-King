package com.King.module;

import com.King.MainInformation;
import com.King.dao.*;
import com.King.enums.ServerId;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import javax.xml.crypto.Data;
import java.util.*;

/**
 * Created by wed on 07.02.16.
 */
public class DataBase {

    private static final SessionFactory ourSessionFactory;
    private static final ServiceRegistry serviceRegistry;
    private static DataBase dataBase=new DataBase();
    private Map<Long,GameBD> _games=new HashMap<Long, GameBD>();
    private Map<String,PlayerBD> _players=new HashMap<String, PlayerBD>();


    static {
        try {
            Configuration configuration = new Configuration();
            configuration.configure();

            serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();
            ourSessionFactory = configuration.buildSessionFactory(serviceRegistry);
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static Session getSession() throws HibernateException {
        return ourSessionFactory.openSession();
    }

    public static DataBase Initialization()
    {
        return dataBase;
    }

    public boolean addGame(GameBD game)
    {
        Session session = getSession();
        try {

            _games.put(game.getIdGame(), game);
            session.beginTransaction();
            session.saveOrUpdate(game);
            session.getTransaction().commit();
            return true;
        }catch (Exception e)
        {
            Log.log(e.toString());
        }finally {
            session.close();
        }
        return false;
    }

    public boolean addConfig(GameBD game)
    {
        Session session = getSession();
        try {
            GameConfigBD conf=game.getGameConfigsById().iterator().next();
            conf.setGameByGameId(game);
            session.beginTransaction();
            session.saveOrUpdate(conf);
            session.getTransaction().commit();
            return true;
        }catch (Exception e)
        {
            Log.log(e.toString());
        }finally {
            session.close();
        }
        return false;
    }

    public boolean addPlayer(GameBD game,GamePlayersBD player)
    {
        Session session = getSession();
        try {
            if(game.getGamePlayersesById()==null)
                game.setGamePlayersesById(new HashSet<GamePlayersBD>());
            game.getGamePlayersesById().add(player);
            player.setGameByGameId(game);
            session.beginTransaction();
            session.saveOrUpdate(player);
            session.getTransaction().commit();
            return true;
        }catch (Exception e)
        {
            Log.log(e.toString());
        }finally {
            session.close();
        }
        return false;
    }

    public boolean addPlayerList(GameBD game)
    {
        if(game.getGamePlayersesById()==null)
            return false;
        Session session = getSession();
        try {
            Iterator<GamePlayersBD> in=game.getGamePlayersesById().iterator();
            session.beginTransaction();
            while (in.hasNext()) {
                GamePlayersBD g=in.next();
                g.setGameByGameId(game);
                session.saveOrUpdate(g);
            }
            session.getTransaction().commit();
            return true;
        }catch (Exception e)
        {
            Log.log(e.toString());
        }finally {
            session.close();
        }
        return false;
    }

    public boolean addBanChampion(GameBD game, GameChampionBanBD ban)
    {
        Session session = getSession();
        try {
            if(game.getGameChampionBansById()==null)
                game.setGameChampionBansById(new HashSet<GameChampionBanBD>());
            game.getGameChampionBansById().add(ban);
            ban.setGameByGameId(game);
            session.beginTransaction();
            session.saveOrUpdate(ban);
            session.getTransaction().commit();
            return true;
        }catch (Exception e)
        {
            Log.log(e.toString());
        }finally {
            session.close();
        }
        return false;
    }

    public boolean addBanChampionList(GameBD game)
    {
        if(game.getGameChampionBansById()==null)
        return false;
        Session session = getSession();
        try {
            Iterator<GameChampionBanBD> in=game.getGameChampionBansById().iterator();
            session.beginTransaction();
            while (in.hasNext()) {
                GameChampionBanBD g=in.next();
                g.setGameByGameId(game);
                session.saveOrUpdate(g);
            }
            session.getTransaction().commit();
            return true;
        }catch (Exception e)
        {
            Log.log(e.toString());
        }finally {
            session.close();
        }
        return false;
    }

    public boolean addPlayer(PlayerBD player)
    {
        Session session = getSession();
        try {

            _players.put(MainInformation.getKeyUser(player.getSummonerId(),player.getServerIdE()), player);
            session.beginTransaction();
            session.saveOrUpdate(player);
            session.getTransaction().commit();
            return true;
        }catch (Exception e)
        {
            Log.log(e.toString());
        }finally {
            session.close();
        }
        return false;
    }

   /* public GameBD getGamebyID(int id)
    {
        return null;
    }*/

    public GameBD getGameByGameId(Long gameid,ServerId id)
    {
        if(_games.containsKey(gameid))
            return _games.get(gameid);

        Session session = getSession();
        try {
            session.beginTransaction();
            session.createCriteria(GameBD.class).add(Restrictions.like("idGame", gameid)).list();
            Query q = session.createQuery(" from GameBD where idGame = :n AND serverId = :s");
            q.setLong("n", gameid);
            q.setString("s", id.toString());
            return (GameBD) q.uniqueResult();
        }catch (Exception e)
        {
            Log.log(e.toString());
        }finally {
            session.close();
        }

        return null;
    }

    public GameBD getGameById(int id)
    {
        Session session = getSession();
        try {
            session.beginTransaction();
            session.createCriteria(GameBD.class).add(Restrictions.like("id", id)).list();
            Query q = session.createQuery(" from GameBD where id = :n");
            q.setInteger("n", id);
            return (GameBD) q.uniqueResult();
        }catch (Exception e)
        {
            Log.log(e.toString());
        }finally {
            session.close();
        }

        return null;
    }

    public List<GameBD> getAllGameById(int id)
    {
        Session session = getSession();
        try {
            session.beginTransaction();
            session.createCriteria(GameBD.class).add(Restrictions.like("id", id)).list();
            Query q = session.createQuery(" from GameBD where id >= :n AND lastChangInfo !=null");
            q.setInteger("n", id);
            return q.list();
        }catch (Exception e)
        {
            Log.log(e.toString());
        }finally {
            session.close();
        }

        return null;
    }

    public GameBD getGameByGameIdWheeEncrypt(Long gameid,ServerId id)
    {
        if(_games.containsKey(gameid) && _games.get(gameid).getLastChangInfo()!=null)
            return _games.get(gameid);

        Session session = getSession();
        try {
            session.beginTransaction();
            session.createCriteria(GameBD.class).add(Restrictions.like("idGame", gameid)).list();
            Query q = session.createQuery(" from GameBD where idGame = :n AND serverId = :s AND lastChangInfo!=null");
            q.setLong("n", gameid);
            q.setString("s", id.toString());
            return (GameBD) q.uniqueResult();
        }catch (Exception e)
        {
            Log.log(e.toString());
        }finally {
            session.close();
        }

        return null;
    }

    public List<PlayerBD> getAllPlayers()
    {
        Session session = getSession();
        try {
            session.beginTransaction();
            session.createCriteria(PlayerBD.class);
            Query q = session.createQuery("From PlayerBD");
            List<PlayerBD> list=q.list();
            for(PlayerBD p:list)
            {
                _players.put(MainInformation.getKeyUser(p.getSummonerId(),p.getServerIdE()),p);
            }
            return q.list();
        }catch (Exception e)
        {
            Log.log(e.toString());
        }finally {
            session.close();
        }
        return null;
    }

    public boolean isPlayerExist(ServerId servId,Long sumId)
    {
        return _players.containsKey(MainInformation.getKeyUser(sumId,servId));
    }
}
