#pragma once

#include "ExecutableFrame.h"

class ReceiptFrame : public ExecutableFrame {
    public:
        ReceiptFrame(unordered_map<string, string> headers, string frameBody);
        void execute();
};