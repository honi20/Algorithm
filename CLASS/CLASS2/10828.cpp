#include <iostream>
#include <vector>
#include <algorithm>
#include <cstring>
#include <cctype>
#include <stack>
#include <map>
using namespace std;
typedef long long ll;

int main(){
    cin.tie(0); cout.tie(0);
    ios_base::sync_with_stdio(false);
    
    int n,x;
    string str;
    stack <int> s;
    
    cin>>n;
    while (n--){
        cin>>str;
        if (str == "push"){
            cin>>x;
            s.push(x);
        }
        else if (str == "pop"){
            if (s.empty())
                cout<<"-1\n";
            else{
                cout<<s.top()<<"\n";
                s.pop();
            }
        }
        
        else if (str=="size")
            cout<<s.size()<<"\n";
        else if (str == "empty"){
            if (s.empty())
                cout<<"1\n";
            else
                cout<<"0\n";
        }
        else if (str == "top"){
            if (s.empty())
                cout<<"-1\n";
            else
                cout<<s.top()<<"\n";
        }
    }
}
