#include <iostream>
#include <map>
using namespace std;

int main(){
    cin.tie(0); cout.tie(0);
    ios::sync_with_stdio(false);
    
    int t,n;
    string name, type;
    cin>>t;
    while (t--){
        cin>>n;
        map<string, int> map;
        long long result = 1;
        
        for (int i=0;i<n;i++){
            cin>>name>>type;
            if (map.find(type)==map.end())
                map[type]=1;
            else
                map[type]++;
        }
        for (auto iter = map.begin(); iter!=map.end(); iter++){
            result *= (iter->second+1);
        }
        cout<<result-1<<"\n";
    }
}
