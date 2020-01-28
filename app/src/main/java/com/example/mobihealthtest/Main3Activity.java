package com.example.mobihealthtest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearSmoothScroller;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;
import androidx.transition.TransitionManager;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;
import com.transitionseverywhere.ChangeText;

import java.util.ArrayList;
import java.util.List;

public class Main3Activity extends AppCompatActivity {

    ViewGroup transitionsContainer;
    TextView tv_description_childcare,tv_title_childcare;
    CoordinatorLayout cl_main;
    RecyclerView rv_maintabs,rv_vaccines2;
    Toolbar tb_2;
    ViewPager vp_cards2;
    TabLayout tl_card_tabs2,tl_card_tabs_expanded;
    int card_position = 0;
    List<List<Integer>> AllCards;

    List<String> MainTabs;

    List<String> titles_cc,description_cc;

    Boolean b_expanded_visible = false;

    //Expanded
    List<CardsModel> cardsModelList;
    public ViewPagerAdapter viewPagerAdapter;
    public VertivalViewPager viewPager;
    TextView tv_title_expaneded,tv_page_counter;
    Toolbar tb_expanded;
    DrawerLayout ll_cards_expanded;
    int Expand_Fade_Duration = 150;
    /*
    Toolbar tb_expanded;
    TextView tv_title_expaneded;
    ViewPager vp_cards_expanded;
    */


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);


        InitializeObjects();
    }

    private void InitializeObjects() {

        transitionsContainer = (ViewGroup)findViewById(R.id.cl_main);
        cl_main = findViewById(R.id.cl_main);
        vp_cards2 = findViewById(R.id.vp_cards2);
        tb_2 = findViewById(R.id.tb_2);

        tv_title_childcare = findViewById(R.id.tv_title_childcare);
        tv_description_childcare = findViewById(R.id.tv_description_childcare);

        rv_maintabs = findViewById(R.id.rv_maintabs);
        rv_vaccines2 = findViewById(R.id.rv_vaccines2);
        tl_card_tabs2 = findViewById(R.id.tl_card_tabs2);
        tl_card_tabs2 = findViewById(R.id.tl_card_tabs2);
        tl_card_tabs2.setupWithViewPager(vp_cards2, true);
        vp_cards2.setClipToPadding(false);

        //Toolbar
        setSupportActionBar(tb_2);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        tb_2.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(Main3Activity.this, "Back", Toast.LENGTH_SHORT).show();
                onBackPressed();
            }
        });


        SetMainTabRecyclerView();
        SetSubTabsRecyclerView();

        SetCardViewPager();


        //Expanded
        ll_cards_expanded = findViewById(R.id.ll_cards_expanded);
        tv_title_expaneded = findViewById(R.id.tv_title_expaneded);
        tb_expanded = findViewById(R.id.tb_expanded);
        tv_page_counter = findViewById(R.id.tv_page_counter);


        /*
        tb_expanded = findViewById(R.id.tb_expanded);
        tv_title_expaneded = findViewById(R.id.tv_title_expaneded);
        vp_cards_expanded = findViewById(R.id.vp_cards_expanded);*/
        //tl_card_tabs_expanded = findViewById(R.id.tl_card_tabs_expanded);

        /*expanded_cards = new ArrayList<>();
        List<Integer> c = new ArrayList<>();
        c.add(R.drawable.bcg_one);
        c.add(R.drawable.bcg_two);
        c.add(R.drawable.bcg_three);
        c.add(R.drawable.bcg_four);
        expanded_cards.add(c);*/


    }

    private void SetCardViewPager() {
        AllCards = new ArrayList<>();
        List<Integer> cards = new ArrayList<>();
        cards.add(R.drawable.one);
        cards.add(R.drawable.two);
        cards.add(R.drawable.three);
        AllCards.add(cards);
        cards = new ArrayList<>();
        cards.add(R.drawable.four);
        cards.add(R.drawable.five);
        cards.add(R.drawable.six);
        AllCards.add(cards);
        cards = new ArrayList<>();
        cards.add(R.drawable.seven);
        cards.add(R.drawable.eight);
        cards.add(R.drawable.nine);
        cards.add(R.drawable.ten);
        AllCards.add(cards);

        SetCardRecylerView(AllCards.get(0));
    }

    private void SetSubTabsRecyclerView() {

        List<String> titles =new ArrayList<>();
        titles.add("0 Birth");
        titles.add("6 Weeks");
        titles.add("10 Weeks");
        titles.add("14 Weeks");
        titles.add("6 Months");
        titles.add("9 Months");
        titles.add("12 Months");
        titles.add("15 Months");
        titles.add("18 Months");
        titles.add("2 Years");
        titles.add("4 Years");
        titles.add("11 Years");
        titles.add("11 Years(2M)");
        titles.add("11 Years(6M)");

        titles_cc = new ArrayList<>();
        description_cc = new ArrayList<>();

        titles_cc.add("At Birth");
        titles_cc.add("At 6 Weeks");
        titles_cc.add("At 10 Weeks");
        titles_cc.add("At 14 Weeks");
        titles_cc.add("At 6 Months");
        titles_cc.add("At 9 Months");
        titles_cc.add("At 12 Months");
        titles_cc.add("At 15 Months");
        titles_cc.add("At 18 Months");
        titles_cc.add("At 2 Years");
        titles_cc.add("At 4 Years");
        titles_cc.add("At 11 Years");
        titles_cc.add("At 11 Years(2M)");
        titles_cc.add("At 11 Years(6M)");

        description_cc.add("Babies get their first vaccinations and preventive care");
        description_cc.add("Sometimes children have mild reactions from vaccines");
        description_cc.add("Read the Vaccine Information Sheet(s) your baby’s doctor");
        description_cc.add("Pay extra attention to your baby for a few days");
        description_cc.add("Use a cool, damp cloth to help reduce redness, soreness");
        description_cc.add("The Centers for Disease Control and Prevention");
        description_cc.add("Protect your baby against 14 potentially serious diseases");
        description_cc.add("At 15 months, your baby should receive vaccines");
        description_cc.add("Vaccines work by imitating infection of a certain disease");
        description_cc.add("With antibodies in place, your child’s body can defeat future infection");
        description_cc.add("Vaccinations aren’t all given right after a baby is born");
        description_cc.add("Vaccines are an important part of keeping your child safe");
        description_cc.add("It’s important that as many people as possible get vaccinated.");
        description_cc.add("Children usually catch these diseases from other children or adults");
        //final SnapHelper snapHelper = new LinearSnapHelper();
        //snapHelper.attachToRecyclerView(rv_vaccines2);

        TabAdapter adapter = new TabAdapter(this, titles, new TabAdapter.OnItemClickListner() {
            @Override
            public void onTabClick(final int position) {
                int p = position%3;
                /*rv_vaccines2.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        rv_vaccines2.smoothScrollToPosition(position);
                    }
                }, 500);*/

                final RecyclerView.LayoutManager lm = rv_vaccines2.getLayoutManager();
                RecyclerView.SmoothScroller smoothScroller = new CenterSmoothScroller(rv_vaccines2.getContext());

                smoothScroller.setTargetPosition(position);

                lm.startSmoothScroll(smoothScroller);

                SetCardRecylerView(AllCards.get(p));
                card_position = p;
                AnimateText(position);
            }
        });

        rv_vaccines2.setAdapter(adapter);

    }

    private void AnimateText(int position) {
        TransitionManager.beginDelayedTransition(transitionsContainer,
                new ChangeText().setChangeBehavior(ChangeText.CHANGE_BEHAVIOR_OUT_IN));
        tv_title_childcare.setText(titles_cc.get(position));
        tv_description_childcare.setText(description_cc.get(position));
    }

    private void SetMainTabRecyclerView() {

        MainTabs = new ArrayList<>();
        MainTabs.add("Vaccination");
        MainTabs.add("Development");
        MainTabs.add("Food");

        MainTabAdapter adapter = new MainTabAdapter(this, MainTabs, new MainTabAdapter.OnItemClickListner() {
            @Override
            public void onTabClick(int position) {


            }
        });


        rv_maintabs.setAdapter(adapter);

    }


    private void SetCardRecylerView(final List<Integer> cardList) {
        final CardAdapterMain mCardAdapterMain = new CardAdapterMain(this, cardList, new CardAdapterMain.OnPageClickListner() {
            @Override
            public void onPageClick(int position) {
                List<Integer> cardlist = AllCards.get(card_position);
                //cardList.get(position);
                /*Animation animation = AnimationUtils.loadAnimation(context,
                        R.anim.zoom_in);
                vp_cards.startAnimation(animation);*/

                CardAdapterMain adapter = (CardAdapterMain) vp_cards2.getAdapter();
                CardView fragment = adapter.getCardViewAt(vp_cards2.getCurrentItem());

                ShowExpandedCards2();

                //ShowExpandedCards(cardlist,position);

                //View activeView = fragment.getView();
                //zoomImageFromThumb(fragment, cardList.get(position));

            }
        });

        ShadowTransformer shadowTransformer = new ShadowTransformer(vp_cards2,mCardAdapterMain);
        shadowTransformer.enableScaling(true);
        vp_cards2.setPageMargin(60);
        vp_cards2.setAdapter(mCardAdapterMain);
        vp_cards2.setPageTransformer(false,shadowTransformer);
        vp_cards2.setOffscreenPageLimit(3);

    }

   /* private void ShowExpandedCards(List<Integer> cardlist,int position) {


        b_expanded_visible = transitions.FadeInOut(ll_cards_expanded,b_expanded_visible,getApplicationContext(),Expand_Fade_Duration);
        *//*List<Integer> cards = new ArrayList<>();
        cards.add(R.drawable.one);
        cards.add(R.drawable.two);
        cards.add(R.drawable.three);*//*

        final CardAdapterMain mCardAdapterMain = new CardAdapterMain(this, expanded_cards.get(0), new CardAdapterMain.OnPageClickListner() {
            @Override
            public void onPageClick(int position) {


            }
        });

        ShadowTransformer shadowTransformer = new ShadowTransformer(vp_cards2,mCardAdapterMain);
        shadowTransformer.enableScaling(true);
        vp_cards_expanded.setPageMargin(60);
        vp_cards_expanded.setAdapter(mCardAdapterMain);
        vp_cards_expanded.setPageTransformer(false,shadowTransformer);
        vp_cards_expanded.setOffscreenPageLimit(3);
        //vp_cards_expanded.setCurrentItem(position);
        tl_card_tabs_expanded.setupWithViewPager(vp_cards_expanded);

        setSupportActionBar(tb_expanded);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        tb_expanded.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setSupportActionBar(tb_2);
                getSupportActionBar().setDisplayShowTitleEnabled(false);
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                getSupportActionBar().setDisplayShowHomeEnabled(true);
                b_expanded_visible = transitions.FadeInOut(ll_cards_expanded,b_expanded_visible,getApplicationContext(),Expand_Fade_Duration);
            }
        });
    }*/


    @Override
    public void onBackPressed() {
        if(b_expanded_visible){

            b_expanded_visible = transitions.FadeInOut(ll_cards_expanded,b_expanded_visible,getApplicationContext(),Expand_Fade_Duration);
        }
        else
            super.onBackPressed();
    }



    public void ShowExpandedCards2(){

        b_expanded_visible = transitions.FadeInOut(ll_cards_expanded,b_expanded_visible,getApplicationContext(),Expand_Fade_Duration);

        setSupportActionBar(tb_expanded);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        SetCards();


    }

    private void SetCards() {

        setSupportActionBar(tb_expanded);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        tb_expanded.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(Main3Activity.this, "back", Toast.LENGTH_SHORT).show();

                onBackPressed();
            }
        });

        cardsModelList = new ArrayList<>();
        List<Integer> tempcards = new ArrayList<>();
        tempcards.add(R.drawable.bcg_one);
        tempcards.add(R.drawable.bcg_two);
        tempcards.add(R.drawable.bcg_three);
        tempcards.add(R.drawable.bcg_four);
        CardsModel c = new CardsModel("BCG",tempcards);
        cardsModelList.add(c);

        tempcards = new ArrayList<>();
        tempcards.add(R.drawable.one);
        tempcards.add(R.drawable.two);
        tempcards.add(R.drawable.three);
        c = new CardsModel("OPV",tempcards);
        cardsModelList.add(c);


        tempcards = new ArrayList<>();
        tempcards.add(R.drawable.five);
        tempcards.add(R.drawable.six);
        tempcards.add(R.drawable.seven);
        tempcards.add(R.drawable.eight);
        c = new CardsModel("HEP B",tempcards);
        cardsModelList.add(c);

        tempcards = new ArrayList<>();
        tempcards.add(R.drawable.nine);
        tempcards.add(R.drawable.ten);
        c = new CardsModel("FLU",tempcards);
        cardsModelList.add(c);

        tempcards = new ArrayList<>();
        tempcards.add(R.drawable.one);
        tempcards.add(R.drawable.two);
        tempcards.add(R.drawable.three);
        tempcards.add(R.drawable.four);
        c = new CardsModel("RV",tempcards);
        cardsModelList.add(c);

        SetViewPager();

    }

    private void SetViewPager() {


        viewPager = (VertivalViewPager) findViewById(R.id.viewPager);
        viewPagerAdapter = new ViewPagerAdapter(this,getSupportFragmentManager(),cardsModelList);
        viewPager.setAdapter(viewPagerAdapter);
        viewPager.setOffscreenPageLimit(3);
        viewPager.setPageMargin(30);
        viewPager.setCurrentItem(0);
        final int total_pages = cardsModelList.size();
        tv_page_counter.setText("1/"+(total_pages));
        tv_title_expaneded.setText(""+cardsModelList.get(0).getTitle());

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                //Toast.makeText(MainActivity.this, ""+position, Toast.LENGTH_SHORT).show();
                //Toast.makeText(Main3Activity.this, ""+cardsModelList.get(position).getTitle(), Toast.LENGTH_SHORT).show();
                tv_title_expaneded.setText(cardsModelList.get(position).getTitle());
                tv_page_counter.setText((position+1)+"/"+(total_pages));
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }


    public class CenterSmoothScroller extends LinearSmoothScroller {

        CenterSmoothScroller(Context context) {
            super(context);
        }

        @Override
        public int calculateDtToFit(int viewStart, int viewEnd, int boxStart, int boxEnd, int snapPreference) {
            return (boxStart + (boxEnd - boxStart) / 2) - (viewStart + (viewEnd - viewStart) / 2);
        }
    }
}
