//
//  main.cpp
//  CLASS3
//
//  Created by 최예헌 on 2022/07/11.
//

#include <iostream>
#include <queue>
using namespace std;

queue<int> q;

void solve(string s){
    int temp;
    if (s == "push"){
        cin>>temp;
        q.push(temp);
    }
    
    else if (s == "pop"){
        if (q.empty())
            cout<<"-1\n";
        else{
            cout<<q.front()<<"\n";
            q.pop();
        }
    }
    
    else if (s == "size"){
        cout<<q.size()<<"\n";
    }
    
    else if (s == "empty"){
        if (q.empty())
            cout<<"1\n";
        else
            cout<<"0\n";
    }
    
    else if (s == "front"){
        if (q.empty())
            cout<<"-1\n";
        else
            cout<<q.front()<<"\n";
    }
    
    else if (s == "back"){
        if (q.empty())
            cout<<"-1\n";
        else
            cout<<q.back()<<"\n";
    }
}

int main(int argc, const char * argv[]) {
    cin.tie(0); cout.tie(0);
    ios::sync_with_stdio(false);
    
    int n;
    string str;
    
    cin>>n;
    while (n--){
        cin>>str;
        solve(str);
    }
    
    return 0;
}
