#include <iostream>
#include <cmath>
using namespace std;
typedef long double ld;

int t, k;
bool check[1002];

int main(){
    cin.tie(0); cout.tie(0);
    ios::sync_with_stdio(false);
 
    cin>>t;
    for (int i=0; i<1002; i++){
        check[i] = false;
    }
    
    for (int i=1; i<=1000; i++){
        for (int j=1; j<=1000; j++){
            for (int k=1; k<=1000; k++){
                int x = (i*(i+1))/2;
                int y = (j*(j+1))/2;
                int z = (k*(k+1))/2;
                
                if (x+y+z > 1000)
                    continue;
                
                check[x+y+z] = true;
            }
        }
    }
    
    while (t--){
        cin>>k;
        if (check[k])
            cout<<"1";
        else
            cout<<"0";
        cout<<"\n";
    }
}
