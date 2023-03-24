#include <iostream>
#include <vector>
#include <map>
using namespace std;
typedef long long ll;

int main(){
    cin.tie(0); cout.tie(0);
    ios::sync_with_stdio(false);
    
    int n, t;
    string str;
    
    cin>>n;
    for (int i=0; i<n; i++){
        map<string,int> armyIdx;
        int armyCnt[100002] = {0,};
        string armyName[100002];
        int tmp = 1;
        
        cin>>t;
        
        
        for (int j=0; j<t; j++){
            cin>>str;
            
            // new army
            if (armyIdx.find(str) == armyIdx.end()){
                armyIdx[str] = tmp;
                armyCnt[tmp]++;
                armyName[tmp] = str;
                tmp++;
            }
            
            else{
                armyCnt[armyIdx[str]]++;
            }
        }
        
        bool flag = false;
        for (int j=0; j<tmp; j++){
            if (flag)
                break;
            
            if (armyCnt[j] > t/2){
                cout<<armyName[j]<<"\n";
                flag = true;
                break;
            }
        }
        
        if (!flag){
            cout<<"SYJKGW\n";
        }
    }
}
