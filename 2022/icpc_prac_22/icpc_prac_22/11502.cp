#include <iostream>
#include <map>
#include <cstring>
using namespace std;
typedef long long ll;

ll num[1001];
ll check[1001][4]={0,};    // 여부, 소수 3개

void init(){
    for (ll i=2; i<1001; i++){
        if (num[i])       //0이 아니면 합성수임
            continue;
        for (ll j=i*i; j<1001; j+=i)    //시간 복잡도 보장
            num[j]=1;
    }
}

int main(){
    cin.tie(0); cout.tie(0);
    ios::sync_with_stdio(false);
 
    int t,n;
    
    init();
    for (int i=2; i<1001; i++){
        if (num[i] != 0)
            continue;
        
        for (int j=2;j<1001;j++){
            if (num[j] != 0)
                continue;
            
            for (int k=2; k<1001; k++){
                if (num[k] != 0)
                    continue;
                
                if (i+j+k > 1000)
                    continue;
                    
                if (check[i+j+k][0] == 0){
                    check[i+j+k][0] = 1;
                    check[i+j+k][1] = i;
                    check[i+j+k][2] = j;
                    check[i+j+k][3] = k;
                }
            }
        }
    }
    
    cin>>t;
    while (t--){
        cin>>n;
        if (check[n][0] == 0)
            cout<<0;
        else
            cout<<check[n][1]<<" "<<check[n][2]<<" "<<check[n][3];
        cout<<"\n";
    }
}
