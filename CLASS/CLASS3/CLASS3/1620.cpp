#include <iostream>
#include <map>
#include <cstring>
using namespace std;

int main(){
    cin.tie(0); cout.tie(0);
    ios::sync_with_stdio(false);
    
    int n,m;
    string str;
    map<string,int> map;
    string arr[100002];
    
    cin>>n>>m;
    for (int i=1;i<=n;i++){
        cin>>str;
        map[str] = i;
        arr[i]=str;
    }
    
    while (m--){
        cin>>str;
        if ('A'<=str[0] && str[0]<='Z'){
            cout<<map[str]<<"\n";
        }
        else{
            int temp = stoi(str);
            cout<<arr[temp]<<"\n";
        }
    }
}
