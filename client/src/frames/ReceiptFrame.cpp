#include "ReceiptFrame.h"

ReceiptFrame::ReceiptFrame(unordered_map<string, string> headers, string frameBody)
    : ExecutableFrame(StompCommand::RECEIPT, headers, frameBody)
{
}

void ReceiptFrame::execute() {
    cout << "~ success"<< endl;
}
