#include <iostream>
#include <vector>
#include <algorithm>
#include <map>
#include <queue>
#include <tuple>
#include <cstring>
#include <string>
using namespace std;
typedef long long ll;

int main(){
    cin.tie(0); cout.tie(0);
    ios::sync_with_stdio(false);

    int arr[8];
    int check;
    for (int i=0;i<8;i++){
        cin>>arr[i];
        
        if (i==0){
            if (arr[i]==1)
                check=1;
            else if (arr[i]==8)
                check=2;
            else
                check=0;
            continue;
        }
        
        if (arr[i-1]+1==arr[i] && check==1){
            continue;
        }
            
        if (arr[i-1]-1==arr[i] && check==2)
            continue;
        
        check=0;
        
    }
    
    if (check==0)
        cout<<"mixed";
    else if (check==1)
        cout<<"ascending";
    else
        cout<<"descending";
}
