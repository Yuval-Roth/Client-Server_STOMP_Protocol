#include "ReceiptFrame.h"

ReceiptFrame::ReceiptFrame(unordered_map<string, string> headers, string frameBody)
    : Frame(StompCommand::RECEIPT, headers, frameBody)
{
}
