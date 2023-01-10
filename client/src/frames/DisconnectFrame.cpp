#include "DisconnectFrame.h"
#include "UserData.h"

DisconnectFrame::DisconnectFrame(StompCommand command, unordered_map<string, string> headers, string frameBody)
    : Frame(command, headers, frameBody){}


DisconnectFrame * DisconnectFrame::get()
{
    unordered_map<string, string> headersMap;
    headersMap["receipt"] = to_string(UserData::getInstance().getReceiptId());
    DisconnectFrame *frame = new DisconnectFrame(StompCommand::DISCONNECT, headersMap, "");
    return frame;
}

