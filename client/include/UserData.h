#pragma once


#include "Frame.h"

class UserData{

    private:
    static UserData* instance;
    UserData();
    ~UserData();
    bool loggedIn;
    string userName;

    public:

    void addAction(Frame frame);
    void setUserName();
    void setLoggedIn(bool loggedIn);
    //rule of 3
    

};
