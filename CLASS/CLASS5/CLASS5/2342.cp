#include <iostream>
using namespace std;

int solve(int now, int next){
    if (now == next)
        return 1;
    else if (now == 0)
        return 2;
    else if (now+next == 4 || now+next == 6)
        return 4;
    else
        return 3;
}

int main(){
    cin.tie(0); cout.tie(0);
    ios::sync_with_stdio(false);
    
    int arr[100002];
    int dp[100002][4][3];
    
    int temp=0;
    while(true){
        cin>>arr[temp];
        if (arr[temp] == 0)
            break;
        temp++;
    }
    
    for (int i=0;i<4;i++){
        if (i<2){
            dp[0][i][0]=arr[0];
            dp[0][i][1]=0;
            dp[0][i][2]=2;
        }
        else{
            dp[0][i][0]=0;
            dp[0][i][1]=arr[0];
            dp[0][i][2]=2;
        }
    }
    
    
    for (int i=1;i<temp;i++){
        int a1 = dp[i-1][0][2]+solve(dp[i-1][0][0], arr[i]);
        int b1 = dp[i-1][1][2]+solve(dp[i-1][1][0], arr[i]);
        
        if (a1<=b1){
            dp[i][0][0]=arr[i];
            dp[i][0][1]=dp[i-1][0][1];
            dp[i][0][2]=a1;
        }
        else{
            dp[i][0][0]=arr[i];
            dp[i][0][1]=dp[i-1][1][1];
            dp[i][0][2]=b1;
        }
        
        int a2 = dp[i-1][2][2]+solve(dp[i-1][2][0], arr[i]);
        int b2 = dp[i-1][3][2]+solve(dp[i-1][3][0], arr[i]);
        
        if (a2<=b2){
            dp[i][1][0]=arr[i];
            dp[i][1][1]=dp[i-1][2][1];
            dp[i][1][2]=a2;
        }
        else{
            dp[i][1][0]=arr[i];
            dp[i][1][1]=dp[i-1][3][1];
            dp[i][1][2]=b2;
        }
        
        int a3 = dp[i-1][0][2]+solve(dp[i-1][0][1], arr[i]);
        int b3 = dp[i-1][1][2]+solve(dp[i-1][1][1], arr[i]);
        
        if (a3<=b3){
            dp[i][2][0]=dp[i-1][0][0];
            dp[i][2][1]=arr[i];
            dp[i][2][2]=a3;
        }
        else{
            dp[i][2][0]=dp[i-1][1][0];
            dp[i][2][1]=arr[i];
            dp[i][2][2]=b3;
        }
        
        int a4 = dp[i-1][2][2]+solve(dp[i-1][2][1], arr[i]);
        int b4 = dp[i-1][3][2]+solve(dp[i-1][3][1], arr[i]);
        
        if (a4<=b4){
            dp[i][3][0]=dp[i-1][2][0];
            dp[i][3][1]=arr[i];
            dp[i][3][2]=a4;
        }
        else{
            dp[i][3][0]=dp[i-1][3][0];
            dp[i][3][1]=arr[i];
            dp[i][3][2]=b4;
        }
    }
    
//    int m1 = min(dp[temp-1][0][2], dp[temp-1][1][2]);
//    int m2 = min(dp[temp-1][2][2], dp[temp-1][3][2]);
//    cout<<min(m1, m2);
    
    for (int i=0;i<temp;i++){
        for (int j=0;j<4;j++){
            for (int k=0;k<3;k++){
                cout<<dp[i][j][k]<<" ";
            }
            cout<<"\n";
        }
        cout<<"\n";
    }
    
}
