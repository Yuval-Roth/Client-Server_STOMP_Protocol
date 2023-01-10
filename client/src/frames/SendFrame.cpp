#include "SendFrame.h"
#include "event.h"
#include <unordered_map>
#include "UserData.h"

SendFrame::SendFrame(StompCommand command, unordered_map<string, string> headers, string frameBody)
    : Frame(command, headers, frameBody){}


SendFrame *SendFrame::get(Event& event)
{
    UserData &userData = UserData::getInstance();
    unordered_map<string, string> headers;
    headers["destination"] = "/" + event.get_team_a_name() + "_" + event.get_team_b_name();
    headers["user"] = userData.getUserName();
    headers["event name"] = event.get_name();
    headers["time"] = to_string(event.get_time());

    map<string, string> gameUpdates = event.get_game_updates();
    string gameUpdatesString;
    for (auto & gameUpdate : gameUpdates) {
        gameUpdatesString += gameUpdate.first + ":" + gameUpdate.second + ",";
    }
    // copilot generated - seems like an iterator over the map and seems to do the trick. check if this the right output
    headers["game updates"] = gameUpdatesString;
    headers["description"] = event.get_discription();
    SendFrame *frame = new SendFrame(StompCommand::SEND, headers, "");
    return frame;
}
