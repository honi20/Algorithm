#include <iostream>
using namespace std;

int n;
int arr[2525][2525];
int cnt1=0, cnt_1=0, cnt0=0;

void solve(int y,int x,int size){
    int temp = arr[y][x];
    bool check=true;
    for (int i=y; i<y+size; i++){
        for (int j=x; j<x+size; j++){
            if (arr[i][j] != temp){
                check=false;
                break;
            }
        }
        if (!check)
            break;
    }
    if (check){
        if (temp == 1)
            cnt1++;
        else if (temp == 0)
            cnt0++;
        else
            cnt_1++;
    }
    else{
        int div = size/3;
        solve(y,x,div);
        solve(y, x+div, div);
        solve(y, x+div*2, div);
        solve(y+div,x,div);
        solve(y+div, x+div, div);
        solve(y+div, x+div*2, div);
        solve(y+div*2,x,div);
        solve(y+div*2, x+div, div);
        solve(y+div*2, x+div*2, div);
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
    cout<<cnt_1<<"\n"<<cnt0<<"\n"<<cnt1;
} 

