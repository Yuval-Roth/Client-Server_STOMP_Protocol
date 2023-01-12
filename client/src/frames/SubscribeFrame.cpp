#include "SubscribeFrame.h"
#include "UserData.h"

SubscribeFrame::SubscribeFrame(unordered_map<string, string> headers, string frameBody)
    : Frame(StompCommand::SUBSCRIBE, headers, frameBody){}


SubscribeFrame *SubscribeFrame::get(string gameName) {
    unordered_map<string, string> headers;
    headers["destination"] = "/" + gameName;
    headers["id"] = to_string(UserData::getInstance().generateSubId(gameName));
    // TODO: check if this is the right way to do it
    headers["receipt"] = to_string(UserData::getInstance().getReceiptId());
    SubscribeFrame *frame = new SubscribeFrame(headers, "");
    return frame;
}


