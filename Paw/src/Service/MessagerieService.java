/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Entity.Messagerie;
import Entity.Utilisateur;
import Utility.DbHandler;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Guideinfo
 */
public class MessagerieService {
    
     protected Connection connection;
    private DbHandler handler;
     private PreparedStatement ps;
     
     
     
    public MessagerieService (){
        handler = DbHandler.getDBHandler();
        connection =handler.getConnection();
        
         
    }
    
    
    
    
      public void ajouterMessage(Messagerie m) {

        PreparedStatement ps = null;
        String req = "INSERT INTO Messagerie(ContenuMsg,dateHeureEnvoi,sender_id,reciever_id,deletedSender,deletedReciever) VALUES (?,?,?,?,?,?)";
        try {
            ps = connection.prepareStatement(req);
            ps.setString(1, m.getContenuMsg());
            ps.setTimestamp(2, m.getDateHeureEnvoi());
            ps.setInt(3, m.getSender_id());
            ps.setInt(4, m.getReciever_id());
            ps.setBoolean(5, m.isDeletedSender());
            ps.setBoolean(6, m.isDeletedReciever()
            );

            //EXECUTION
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
    public void supprimerMessage(Messagerie m, int user_id) {
        try {
            String sql = "";
            if (m.getSender_id() == user_id) {
                //System.out.println("message " + m.getId() + " deleted sender");
                m.setDeletedSender(true);
            } else {
                //System.out.println("message " + m.getId() + " deleted reciever");
                m.setDeletedReciever(true);
            }
            PreparedStatement ps = connection.prepareStatement("update messagerie set deletedSender=?,deletedReciever=? where id=?");
            ps.setBoolean(1, m.isDeletedSender());
            ps.setBoolean(2, m.isDeletedReciever());
            ps.setInt(3, m.getId());
            ps.executeUpdate();

        } catch (Exception exx) {
            exx.printStackTrace();
        }

    }
    
     public List<Messagerie> getAll() {
        String req = " select * from messagerie ";
        List<Messagerie> messages = new ArrayList<>();
        try {
            ps = connection.prepareStatement(req);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                Messagerie m = new Messagerie(resultSet.getInt(1), resultSet.getString(2),
                        resultSet.getInt(4),
                        resultSet.getInt(5), resultSet.getBoolean(6), resultSet.getBoolean(7));
                messages.add(m);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return messages;
    }

    
    public List<Messagerie> getConversation(int user1, int user2) {
        String req2 = "select * from messagerie where sender_id=? and reciever_id=? or sender_id=? and reciever_id=? order by dateHeureEnvoi desc ";
        List<Messagerie> discussion = new ArrayList<>();
        try {
            ps = connection.prepareStatement(req2);
            ps.setInt(1, user1);
            ps.setInt(2, user2);
            ps.setInt(3, user2);
            ps.setInt(4, user1);
            ResultSet rs2 = ps.executeQuery();
            while (rs2.next()) {
                Messagerie m = new Messagerie();
                m.setContenuMsg(rs2.getString("ContenuMsg"));
                m.setDeletedReciever(rs2.getBoolean("deletedReciever"));
                m.setDeletedSender(rs2.getBoolean("deletedSender"));
                m.setSender_id(rs2.getInt("sender_id"));
                m.setReciever_id(rs2.getInt("reciever_id"));
                m.setDateHeureEnvoi(rs2.getTimestamp("dateHeureEnvoi"));
                m.setId(rs2.getInt("id"));
                discussion.add(m);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return discussion;
    }

    
     public Map<Utilisateur, Messagerie> getDiscussions(int idUser) {
        UtilisateurServices service = new UtilisateurServices();
        try {
            Map<Utilisateur, Messagerie> messages = new HashMap<Utilisateur, Messagerie>();
            PreparedStatement ps1 = connection.prepareStatement("select sender_id, reciever_id from messagerie where"
                    + " sender_id=? and deletedSender=0  or reciever_id=? and deletedReciever=0  order by dateHeureEnvoi desc");
            ps1.setInt(1, idUser);
            ps1.setInt(2, idUser);
            ps1.execute();
            ResultSet users = ps1.getResultSet();

            while (users.next()) {
                int sender_id = users.getInt("sender_id");
                int reciever_id = users.getInt("reciever_id");
                Utilisateur u;
                Messagerie m = new Messagerie();
                if (sender_id == idUser) {
                    u = service.rechercher(reciever_id);
                    PreparedStatement ps2 = connection.prepareStatement("SELECT * from messagerie where sender_id=? and reciever_id=? "
                            + " or sender_id=? and reciever_id=?  order by dateHeureEnvoi desc");
                    ps2.setInt(1, sender_id);
                    ps2.setInt(2, reciever_id);
                    ps2.setInt(4, sender_id);
                    ps2.setInt(3, reciever_id);
                    ps2.execute();
                    ResultSet rs2 = ps2.getResultSet();
                    if (rs2.next()) {
                        m.setContenuMsg(rs2.getString("ContenuMsg"));
                        m.setDeletedReciever(rs2.getBoolean("deletedReciever"));
                        m.setDeletedSender(rs2.getBoolean("deletedSender"));
                        m.setSender_id(rs2.getInt("sender_id"));
                        m.setReciever_id(rs2.getInt("reciever_id"));
                    }
                } else {
                    u = service.rechercher(sender_id);
                    PreparedStatement ps2 = connection.prepareStatement("SELECT * from messagerie where sender_id=? and reciever_id=? "
                            + " or sender_id=? and reciever_id=?  order by dateHeureEnvoi desc");
                    ps2.setInt(1, sender_id);
                    ps2.setInt(2, reciever_id);
                    ps2.setInt(4, sender_id);
                    ps2.setInt(3, reciever_id);
                    ps2.execute();
                    ResultSet rs2 = ps2.getResultSet();
                    if (rs2.next()) {
                        m.setContenuMsg(rs2.getString("ContenuMsg"));
                        m.setDeletedReciever(rs2.getBoolean("deletedReciever"));
                        m.setDeletedSender(rs2.getBoolean("deletedSender"));
                        m.setSender_id(rs2.getInt("sender_id"));
                        m.setReciever_id(rs2.getInt("reciever_id"));
                    }
                }
                if (messages.containsKey(u) == false) {
                    messages.put(u, m);
                }

            }
            return messages;

        } catch (SQLException ex) {
            Logger.getLogger(MessagerieService.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;

    }

    
    
    
    
    
    
    
    
}
