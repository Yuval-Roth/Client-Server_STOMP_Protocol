#include "SendFrame.h"
#include "event.h"
#include <unordered_map>
#include "UserData.h"

SendFrame::SendFrame(unordered_map<string, string> headers, string frameBody)
    : Frame(StompCommand::SEND, headers, frameBody){}


SendFrame *SendFrame::get(Event& event)
{
    unordered_map<string, string> headers;
    headers["destination"] = "/" + event.get_team_a_name() + "_" + event.get_team_b_name();
    string body = event.extractFrameBody();
    return new SendFrame(headers, body);
}
