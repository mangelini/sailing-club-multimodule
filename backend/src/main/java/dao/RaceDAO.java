package dao;

import common.DBUtil;
import entities.Race;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class RaceDAO {
    /**
     * Add new race record to db
     * @param race Entity that will be added
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public static synchronized void insertRace(Race race) throws SQLException, ClassNotFoundException {
        String updateStmt = "INSERT INTO race (Name, Location, Date, Expired) "
                + "VALUES ('" + race.getName() + "', '" + race.getLocation() + "', '"
                + race.getDate() + "', 0)";

        try {
            DBUtil.dbExecuteUpdate(updateStmt);
        } catch (SQLException e) {
            System.out.print("Error occurred while INSERT Operation: " + e);
            throw e;
        }
    }

    /**
     * Search for a specific race within Race DB's table
     * @param name Name of searched race
     * @return Record of search result
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public static synchronized Race searchRaceByName(String name) throws SQLException, ClassNotFoundException {
        String selectStmt = "SELECT * FROM race WHERE Name='"+name +"'";

        try {
            //Get ResultSet from dbExecuteQuery method
            ResultSet rsRace = DBUtil.dbExecuteQuery(selectStmt);

            //Send ResultSet to the getRaceFromResultSet method and get race object
            Race race = getRaceFromResultSet(rsRace);

            return race;
        } catch (SQLException e) {
            System.out.println("While searching a race with " + name + " name, an error occurred: " + e);
            //Return exception
            throw e;
        }
    }

    /**
     * Search for a specific race within Race DB's table
     * @param ID ID of searched race
     * @return Record of search result
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public static synchronized Race searchRaceByID(Integer ID) throws SQLException, ClassNotFoundException {
        String selectStmt = "SELECT * FROM race WHERE ID='"+ID +"'";

        try {
            //Get ResultSet from dbExecuteQuery method
            ResultSet rsRace = DBUtil.dbExecuteQuery(selectStmt);

            //Send ResultSet to the getRaceFromResultSet method and get race object
            Race race = getRaceFromResultSet(rsRace);

            return race;
        } catch (SQLException e) {
            System.out.println("While searching a race with " + ID + " ID, an error occurred: " + e);
            //Return exception
            throw e;
        }
    }

    public static synchronized ArrayList<Race> getAllRaces() throws SQLException, ClassNotFoundException {
        String selectStmt = "SELECT * FROM race";

        try {
            //Get ResultSet from dbExecuteQuery method
            ResultSet rsRaces = DBUtil.dbExecuteQuery(selectStmt);

            //Send ResultSet to the getRaceFromResultSet method and get race object
            ArrayList<Race> races = getRacesFromResultSet(rsRaces);

            return races;
        } catch (SQLException e) {
            System.out.println("While getting all races, an error occurred: " + e);
            throw e;
        }
    }

    public static synchronized ArrayList<Race> getRacesNotExpired() throws SQLException, ClassNotFoundException {
        String selectStmt = "SELECT * FROM race WHERE Expired=0";

        try {
            //Get ResultSet from dbExecuteQuery method
            ResultSet rsRaces = DBUtil.dbExecuteQuery(selectStmt);

            //Send ResultSet to the getRaceFromResultSet method and get race object
            ArrayList<Race> races = getRacesFromResultSet(rsRaces);

            return races;
        } catch (SQLException e) {
            System.out.println("While getting all races, an error occurred: " + e);
            throw e;
        }
    }

    public static synchronized void updateExpirationField() throws SQLException, ClassNotFoundException {
        String updateStmt = "UPDATE race SET Expired=1 WHERE timestampdiff(second, Date, NOW()) > 150";

        // Execute UPDATE operation
        try {
            DBUtil.dbExecuteUpdate(updateStmt);
        } catch (SQLException e) {
            System.out.print("Error occurred while UPDATE Operation: " + e);
            throw e;
        }
    }

    private static Race getRaceFromResultSet(ResultSet rs) throws SQLException, ClassNotFoundException {
        Race race = null;

        if (rs.next()) {
            race = new Race(rs.getString("Name"), rs.getString("Location"), rs.getTimestamp("Date"), rs.getBoolean("Expired"));
            race.setID(rs.getInt("ID"));
        }

        return race;
    }

    private static ArrayList<Race> getRacesFromResultSet(ResultSet rs) throws SQLException, ClassNotFoundException {
        ArrayList<Race> races = new ArrayList<>();

        while (rs.next()){
            races.add(new Race(rs.getInt("ID"), rs.getString("Name"), rs.getString("Location"), rs.getTimestamp("Date"), rs.getBoolean("Expired")));
        }

        return races;
    }
}
