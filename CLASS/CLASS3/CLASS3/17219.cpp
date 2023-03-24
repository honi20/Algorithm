#include <iostream>
#include <map>
using namespace std;

int main(){
    cin.tie(0); cout.tie(0);
    ios::sync_with_stdio(false);
    
    int n,m;
    map<string,string> map;
    string address, password;
    
    cin>>n>>m;
    for (int i=0;i<n;i++){
        cin>>address>>password;
        map.insert({address, password});
    }
    
    while (m--){
        cin>>address;
        cout<<map[address]<<"\n";
    }
}
