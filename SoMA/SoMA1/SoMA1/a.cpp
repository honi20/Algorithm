#include <iostream>
#include <vector>
#include <cmath>
#include <string>
using namespace std;
typedef long long ll;

int main(){
    cin.tie(0); cout.tie(0);
    ios::sync_with_stdio(false);
    
    string s, t;
    int p = 0;
    
    while (cin>>s>>t){
        p = 0;
        for (int i=0; i<t.size(); i++){
            if (t[i] == s[p])
                p++;
        }
        
        if (p == s.size())
            cout<<"Yes\n";
        else
            cout<<"No\n";
    }
    
    return 0;
}
