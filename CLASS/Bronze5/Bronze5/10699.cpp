//
//  10699.cpp
//  Bronze5
//
//  Created by 최예헌 on 2022/07/11.
//

#include <iostream>
#include <ctime>
#include <string>
using namespace std;

int main(int argc, const char * argv[]) {
    cin.tie(0); cout.tie(0);
    ios::sync_with_stdio(false);
    
    time_t date = time(NULL);
    struct tm* t = localtime(&date);
    
    string year = to_string(t->tm_year+1900);
    string month = to_string(t->tm_mon+1);
    string day = to_string(t->tm_mday);
    
    if (month.size()==1){
        if (day.size() == 1){
            cout<<year<<"-0"<<month<<"-0"<<day;
        }
        else
            cout<<year<<"-0"<<month<<"-"<<day;
    }
    else{
        if (day.size() == 1){
            cout<<year<<"-"<<month<<"-0"<<day;
        }
        else
            cout<<year<<"-"<<month<<"-"<<day;
    }
    
    return 0;
}
