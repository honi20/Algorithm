#include <iostream>
#include <map>
#include <cstring>
using namespace std;
typedef long long ll;

map <string, string> m;

void init(){
    m["a"] = "as";
    m["i"] = "ios";
    m["y"] = "ios";
    m["l"] = "les";
    m["n"] = m["ne"] = "anes";
    m["o"] = "os";
    m["r"] = "res";
    m["t"] = "tas";
    m["u"] = "us";
    m["v"] = "ves";
    m["w"] = "was";
    return;
}

int main(){
    cin.tie(0); cout.tie(0);
    ios::sync_with_stdio(false);
 
    int t;
    string str;
    
    cin>>t;
    init();
    while (t--){
        cin>>str;
        ll size = str.size();
        string s1 = " ", s2 = " ";
        s1 = (str[size-1]);
        s2 = (str[size-2]);
        
        if (m.find(s1) != m.end()){
            for (int i=0; i<size-1; i++)
                cout<<str[i];
            cout<<m[s1]<<"\n";
        }
        else if (m.find(s2+s1) != m.end()){
            for (int i=0; i<size-2; i++)
                cout<<str[i];
            cout<<m[s2+s1]<<"\n";
        }
        else{
            for (int i=0; i<size; i++)
                cout<<str[i];
            cout<<"us\n";
        }
    }
}
