#include <iostream>
using namespace std;

int main(){
    cin.tie(0); cout.tie(0);
    ios::sync_with_stdio(false);
    
    int t,x;
    string str;
    int result=0;
    
    cin>>t;
    while (t--){
        cin>>str;
        if (str == "add"){
            cin>>x;
            x--;
            if (result & (1<<x))
                continue;
            
            result |= (1<<x);
        }
        
        else if (str == "remove"){
            cin>>x;
            x--;
            if (!(result & (1<<x)))
                continue;
            
            result &= ~(1<<x);
        }
        else if (str == "check"){
            cin>>x;
            x--;
            if (result & (1<<x))
                cout<<"1\n";
            else
                cout<<"0\n";
        }
        else if (str == "toggle"){
            cin>>x;
            x--;
            if (result & (1<<x))
                result &= ~(1<<x);
            else
                result |= (1<<x);
        }
        else if (str == "all"){
            result = (1<<20)-1;
        }
        else if (str == "empty"){
            result = 0;
        }
    }
}
