package top.cyixlq.store;

import top.cyixlq.cy.activities.ProxyActivity;
import top.cyixlq.cy.delegates.CyDelegate;

public class MainActivity extends ProxyActivity {

    @Override
    public CyDelegate setRootdelegate() {
        return new TestDelegate();
    }

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//        //Toast.makeText(Cy.getApplicationContext(), "传入Context啦！", Toast.LENGTH_SHORT).show();
//    }
}
