#include <iostream>
using namespace std;

int n;
int arr[1001][1001];
int cnt[2]={0,};

void solve(int y, int x, int size){
    int temp = arr[y][x];
    bool check=true;
    for (int i=y;i<y+size;i++){
        for (int j=x;j<x+size;j++){
            if (temp != arr[i][j]){
                check = false;
                break;
            }
        }
        if (!check)
            break;
    }
    if (check){
        cnt[temp]++;
    }
    else{
        int div = size/2;
        solve(y,x,div);
        solve(y,x+div, div);
        solve(y+div, x,div);
        solve(y+div, x+div, div);
    }
}

int main(){
    cin.tie(0); cout.tie(0);
    ios::sync_with_stdio(false);
    
    cin>>n;
    for (int i=0;i<n;i++){
        for (int j=0;j<n;j++){
            cin>>arr[i][j];
        }
    }
    solve(0,0,n);
    cout<<cnt[0]<<"\n"<<cnt[1];
}

 
