#include <iostream>
#include <vector>
#include <algorithm>
#include <map>
#include <queue>
#include <tuple>
#include <cstring>
#include <string>
#include <stack>
using namespace std;
typedef long long ll;

int main(){
    cin.tie(0); cout.tie(0);
    ios::sync_with_stdio(false);

    string str;
    int t;
    
    cin>>t;
    while (t--){
        cin>>str;
        int result=0;
        stack<int> s;
        s.push(0);
        
        for (int i=0;i<str.size();i++){
            if (str[i]=='O'){
                if (s.top()==0){
                    s.push(1);
                    result+=1;
                }
                else{
                    s.push(s.top()+1);
                    result+=s.top();
                }
            }
            else{
                s.push(0);
            }
        }
        
        cout<<result<<"\n";
    }
}
