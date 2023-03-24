#include <iostream>
using namespace std;

int main(){
    cin.tie(0); cout.tie(0);
    ios::sync_with_stdio(false);
    
    int n,m;
    int x,y;
    int arr[102][102];
    
    cin>>n>>m;
    for (int i=0;i<102;i++){
        for (int j=0;j<102;j++){
            if (i == j)
                arr[i][j]=0;
            else
                arr[i][j]=6000;
        }
    }
    
    for (int i=0;i<m;i++){
        cin>>x>>y;
        arr[x][y]=1;
        arr[y][x]=1;
    }
    
    for (int k=1;k<=n;k++){
        for (int i=1;i<=n;i++){
            for (int j=1;j<=n;j++){
                if (i==j)
                    continue;
                arr[i][j]=arr[j][i]=min(arr[i][j], arr[i][k]+arr[k][j]);
            }
        }
    }
    
    int minsum=6000;
    int result=0;
    for (int i=1;i<=n;i++){
        int sum=0;
        for (int j=1;j<=n;j++){
            sum+=arr[i][j];
        }
        if (sum<minsum){
            minsum=sum;
            result=i;
        }
    }
    cout<<result;
}

