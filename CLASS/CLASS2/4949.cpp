//
//  4949.cpp
//  CLASS2
//
//  Created by 최예헌 on 2022/07/12.
//

#include <iostream>
#include <stack>
using namespace std;

int main(){
    cin.tie(0); cout.tie(0);
    ios::sync_with_stdio(false);
    
    string str;
    
    while (true){
        getline(cin, str);
        stack<char> s;
        
        if (str == ".")
            break;

        bool check = true;
        for (int i=0;i<str.size();i++){
            if (str[i] == '(' || str[i] == '[')
                s.push(str[i]);

            else if (str[i] == ')'){
                if (s.empty() || s.top() != '('){
                    check = false;
                    break;
                }
                else
                    s.pop();
            }

            else if (str[i] == ']'){
                if (s.empty() || s.top() != '['){
                    check = false;
                    break;
                }
                else
                    s.pop();
            }
        }
        
        if (check && s.empty())
            cout<<"yes\n";

        else
            cout<<"no\n";
    }
}

