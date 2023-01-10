#include "SubscribeFrame.h"
#include "UserData.h"

SubscribeFrame::SubscribeFrame(StompCommand command, unordered_map<string, string> headers, string frameBody)
    : Frame(command, headers, frameBody){}


SubscribeFrame *SubscribeFrame::get(string gameName) {
    unordered_map<string, string> headers;
    headers["destination"] = "/" + gameName;
    headers["id"] = to_string(UserData::getInstance().generateSubId(gameName));
    // TODO: check if this is the right way to do it
    headers["receipt"] = to_string(UserData::getInstance().getReceiptId());
    SubscribeFrame *frame = new SubscribeFrame(StompCommand::SUBSCRIBE, headers, "");
    return frame;
}


