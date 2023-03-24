#include <iostream>
#include <cmath>
using namespace std;
typedef long double ld;

int main(){
    cin.tie(0); cout.tie(0);
    ios::sync_with_stdio(false);
 
    int t, n, m;
    string x, y, z;
    char arr[102] = {0,};
    
    cin>>t;
    while (t--){
        int result = 0;
        x = "", y = "";
        
        cin>>n>>m;
        
        for (int i=0; i<m; i++){
            cin>>z;
            x += z;
        }
        
        for (int i=0; i<m; i++){
            cin>>z;
            y += z;
        }

        for (int i=0; i<n; i++){
            cin>>arr[i];
        }
        
        for (int i=0; i<n; i++){
            int idx = i;
            string temp = "";
            
            for (int k=0; k<m; k++){
                temp += arr[idx];
                idx++;
                if (idx == n)
                    idx = 0;
            }
            
            if (stoi(x) <= stoi(temp) && stoi(temp) <= stoi(y))
                result++;
        }
        cout<<result<<"\n";
    }
}
