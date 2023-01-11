#include "UnsubscribeFrame.h"
#include "UserData.h"

const string RECEIPT_HEADER = "receipt"; // This should ideally be in a constants file or Frame class
const string ID_HEADER = "id";
const string DESTINATION_HEADER = "destination";

UnsubscribeFrame::UnsubscribeFrame(unordered_map<string, string> headers, string frameBody)
    : Frame(StompCommand::UNSUBSCRIBE, headers, frameBody){}

UnsubscribeFrame *UnsubscribeFrame::get(string topic)
{
    UserData &userData = UserData::getInstance();
    unordered_map<string, string> headers;
    int subID = userData.getSubId(topic);
    int receiptID = userData.getReceiptId();
    headers[RECEIPT_HEADER] = to_string(receiptID);
    headers[ID_HEADER] = to_string(subID);
    headers[DESTINATION_HEADER] = "/" + topic; // This is a bit ugly
    return new UnsubscribeFrame(headers, "");
}
