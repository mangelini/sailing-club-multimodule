package dao;

import common.DBUtil;
import entities.Race;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RaceDAO {
    /**
     * Add new race record to db
     * @param race Entity that will be added
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public static void insertRace(Race race) throws SQLException, ClassNotFoundException {
        String updateStmt = "INSERT INTO race (Name, Location, Date) "
                + "VALUES ('" + race.getName() + "', '" + race.getLocation() + "', '"
                + race.getDate() + "')";

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
    public static Race searchRaceByName(String name) throws SQLException, ClassNotFoundException {
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
    public static Race searchRaceByID(Integer ID) throws SQLException, ClassNotFoundException {
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

    private static Race getRaceFromResultSet(ResultSet rs) throws SQLException, ClassNotFoundException {
        Race race = null;

        if (rs.next()) {
            java.sql.Date sqlDate=new java.sql.Date(rs.getDate("Date").getTime());
            race = new Race(rs.getString("Name"), rs.getString("Location"), sqlDate);
            race.setID(rs.getInt("ID"));
        }

        return race;
    }
}
