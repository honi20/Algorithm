#include <iostream>
using namespace std;

int main(){
    cin.tie(0); cout.tie(0);
    ios::sync_with_stdio(false);
    
    int t,n;
    long long sum[42][2];
    
    sum[0][0]=1; sum[0][1]=0;
    sum[1][0]=0; sum[1][1]=1;
    
    for (int i=2;i<42;i++){
        sum[i][0]=sum[i-1][0]+sum[i-2][0];
        sum[i][1]=sum[i-1][1]+sum[i-2][1];
    }
    
    cin>>t;
    while(t--){
        cin>>n;
        cout<<sum[n][0]<<" "<<sum[n][1]<<"\n";
    }
}
