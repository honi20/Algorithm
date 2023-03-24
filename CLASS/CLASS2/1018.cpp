#include <iostream>
#include <vector>
#include <algorithm>
#include <cmath>
#include <queue>
#include <tuple>
#include <cstring>
#include <string>
#include <stack>
using namespace std;
typedef long long ll;
#define MAX 1000000001

int main(){
    cin.tie(0); cout.tie(0);
    ios::sync_with_stdio(false);

    int n,m,temp,cnt,flag;
    int arr[52][52]={0,};       // W: 1 , B: 2
    char x;
    int result = 2500;
    
    cin>>n>>m;
    for (int i=1;i<=n;i++){
        for (int j=1;j<=m;j++){
            cin>>x;
            if (x == 'W')
                arr[i][j]=1;
            else
                arr[i][j]=2;
        }
    }
    
    for (int i=1;i<=n-7;i++){
        for (int j=1;j<=m-7;j++){
            temp = 1;       //첫 칸이 W인 경우
            cnt=0;
            for (int a=0;a<8;a++){
                for (int b=0;b<8;b++){
                    if ((a+b)%2==0){
                        if (temp != arr[i+a][j+b])
                            cnt++;
                    }
                    else{
                        if (temp == arr[i+a][j+b])
                            cnt++;
                    }
                }
            }
            result = min(result,cnt);
            
            temp = 2;       //첫 칸이 B인 경우
            cnt=0;
            for (int a=0;a<8;a++){
                for (int b=0;b<8;b++){
                    if ((a+b)%2==0){
                        if (temp != arr[i+a][j+b])
                            cnt++;
                    }
                    else{
                        if (temp == arr[i+a][j+b])
                            cnt++;
                    }
                }
            }
            result = min(result,cnt);
        }
    }
    cout<<result;
}
