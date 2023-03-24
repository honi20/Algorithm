#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

int arr[51][51];
int solve(int x,int y);

int main(){
    cin.tie(0); cout.tie(0);
    ios::sync_with_stdio(false);
    
    int n,m;
    char x;
    int cnt;
    int min=2500;
    
    cin>>n>>m;
    for (int i=1;i<=n;i++){
        for (int j=1;j<=m;j++){     //검은색 0, 흰색 1
            cin>>x;
            if (x=='B')
                arr[i][j]=0;
            else if (x=='W')
                arr[i][j]=1;
        }
    }
    
    for (int i=1;i<=n-7;i++){
        for (int j=1;j<=m-7;j++){
            cnt=solve(i,j);
            if (cnt<min)
                min=cnt;
        }
    }
    cout<<min;
}

int solve(int x,int y){
    int check;
    int cnt;
    int result;
    
    check=0;
    cnt=0;
    for (int i=x;i<=x+7;i++){
        for (int j=y;j<=y+7;j++){
            if (check == arr[i][j])
                cnt++;
            
            if (j!=(y+7)){
                if (check==0)
                    check=1;
                else
                    check=0;
            }
        }
    }
    result=cnt;
    
    check=1;
    cnt=0;
    for (int i=x;i<=x+7;i++){
        for (int j=y;j<=y+7;j++){
            if (check == arr[i][j])
                cnt++;
            
            if (j!=(y+7)){
                if (check==0)
                    check=1;
                else
                    check=0;
            }
        }
    }
    if (cnt<result)
        result=cnt;
    
    return result;
}
