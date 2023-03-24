#include <iostream>
using namespace std;

int main(){
    cin.tie(0); cout.tie(0);
    ios::sync_with_stdio(false);
    
    int n;
    int sum[1000002];
    
    sum[0]=0; sum[1]=0; sum[2]=1; sum[3]=1;
    
    for (int i=4; i<1000002; i++){
        
        sum[i]=1000002;
        sum[i] = min(sum[i], 1+sum[i-1]);
        
        if (i%2 == 0){
            sum[i] = min(sum[i], 1+sum[i/2]);
        }
        
        if (i%3 == 0){
            sum[i] = min(sum[i], 1+sum[i/3]);
        }
    }
    cin>>n;
    cout<<sum[n];
}
