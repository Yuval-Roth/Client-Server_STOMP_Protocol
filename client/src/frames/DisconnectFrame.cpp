#include "DisconnectFrame.h"
#include "UserData.h"

DisconnectFrame::DisconnectFrame(unordered_map<string, string> headers, string frameBody)
    : Frame(StompCommand::DISCONNECT, headers, frameBody){}


DisconnectFrame * DisconnectFrame::get()
{
    unordered_map<string, string> headersMap;
    headersMap["receipt"] = to_string(UserData::getInstance().getReceiptId());
    DisconnectFrame *frame = new DisconnectFrame(headersMap, "");
    return frame;
}

