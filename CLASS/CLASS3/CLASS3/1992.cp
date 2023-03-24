#include <iostream>
#include <cstring>
using namespace std;

int n;
char arr[65][65];

void solve(int y, int x, int size){
    if (size == 1){
        cout<<arr[y][x];
        return;
    }
    
    bool check = true;
    char temp = arr[y][x];
    for (int i=y; i<y+size; i++){
        for (int j=x; j<x+size; j++){
            if (temp != arr[i][j]){
                check=false;
                break;
            }
        }
        if (!check)
            break;
    }
    
    if (check){
        cout<<temp;
    }
    
    else{
        cout<<"(";
        solve(y,x,size/2);
        solve(y,x+size/2,size/2);
        solve(y+size/2, x, size/2);
        solve(y+size/2, x+size/2, size/2);
        cout<<")";
    }
    return;
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
}

