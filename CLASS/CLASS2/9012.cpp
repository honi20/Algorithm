#include <iostream>
#include <vector>
#include <algorithm>
#include <cstring>
#include <cctype>
#include <stack>
#include <deque>
using namespace std;
typedef long long ll;


int main(){
    cin.tie(0); cout.tie(0);
    ios_base::sync_with_stdio(false);
    
    int n;
    string str;
    stack <string> s;
    
    cin>>n;
    for (int i=0;i<n;i++){
        cin>>str;
        bool check=true;
        
        for (int j=0;j<str.size();j++){
            if (str[j]=='(')
                s.push("(");
            
            else if (str[j]==')'){
                if (s.empty() || s.top()!="("){
                    cout<<"NO\n";
                    check=false;
                    break;
                }
                else
                    s.pop();
            }
        }
        if (check){
            if (s.empty())
                cout<<"YES\n";
            else
                cout<<"NO\n";
        }
        while(!s.empty())
            s.pop();
    }
    
}
