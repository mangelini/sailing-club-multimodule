package messageManagement.member;

import dao.BoatDAO;
import dao.RaceDAO;
import dao.RegistrationFeeDAO;
import entities.Boat;
import entities.Member;
import entities.Race;
import messageManagement.Command;
import messageManagement.Message;
import messageManagement.Reply;
import messageManagement.ReplyType;

import java.util.ArrayList;

/**
 * Command that returns a list of races that are not expired and to which
 * the member didn't already register a boat
 */
public class GetAvailableRacesCommand implements Command {
    @Override
    public synchronized Reply execute(Message message) {
        Reply replyMessage = null;
        ArrayList<Race> races = new ArrayList<>();

        try {
            RaceDAO.updateExpirationField();

            // assert that member did not already register any boat
            // to race
            Member member = (Member) message.getUser();
            ArrayList<Boat> boatsOfMember = BoatDAO.searchEnabledBoatsByMember(member);
            races = RaceDAO.getRacesNotExpired();

            for (Race race : races) {
                for (Boat boat : boatsOfMember) {
                    if (RegistrationFeeDAO.boatAlreadyRegistered(boat.getID(), race.getID()))
                        races.remove(race);
                }
            }
        } catch (Exception e) {
            replyMessage = new Reply(ReplyType.ERROR);
            return replyMessage;
        } finally {
            replyMessage = new Reply(ReplyType.OK, races);
        }

        return replyMessage;
    }
}
