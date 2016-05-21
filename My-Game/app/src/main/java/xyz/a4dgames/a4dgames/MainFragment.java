package xyz.a4dgames.a4dgames;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.flyco.tablayout.SegmentTabLayout;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.flyco.tablayout.widget.MsgView;
import com.gigamole.library.NavigationTabBar;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;
import com.rengwuxian.materialedittext.MaterialEditText;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import xyz.a4dgames.a4dgames.adapter.RankAdapter;
import xyz.a4dgames.a4dgames.model.RankItem;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MainFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MainFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MainFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private ViewPager viewPager;
    private FloatingActionButton fab;
    private NavigationTabBar navigationTabBar;
    private MaterialCalendarView widget;
    private String dateInCalendarDialog = "CLICK TO ADD";

    private OnFragmentInteractionListener mListener;


    private void initUI(final View view) {
        viewPager = (ViewPager) view.findViewById(R.id.vp_horizontal_ntb);
        fab = (FloatingActionButton) view.findViewById(R.id.fab);
        navigationTabBar = (NavigationTabBar) view.findViewById(R.id.ntb_horizontal);

        viewPager.setAdapter(mPagerAdapter);

        final String[] colors = getResources().getStringArray(R.array.default_preview);
        final ArrayList<NavigationTabBar.Model> models = new ArrayList<>();
        models.add(new NavigationTabBar.Model(
                getResources().getDrawable(R.drawable.ic_first), Color.parseColor(colors[0]), "Latest"));
        models.add(new NavigationTabBar.Model(
                getResources().getDrawable(R.drawable.ic_third), Color.parseColor(colors[1]), "Past"));
        models.add(new NavigationTabBar.Model(
                getResources().getDrawable(R.drawable.ic_second), Color.parseColor(colors[2]), "Ranking"));
        models.add(new NavigationTabBar.Model(
                getResources().getDrawable(R.drawable.ic_fourth), Color.parseColor(colors[3]), "Bet Now"));
        models.add(new NavigationTabBar.Model(
                getResources().getDrawable(R.drawable.ic_fifth), Color.parseColor(colors[4]), "Omg"));
        navigationTabBar.setModels(models);
        navigationTabBar.setViewPager(viewPager, 0); // set current page
        navigationTabBar.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(final int position, final float positionOffset, final int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(final int position) {
                navigationTabBar.getModels().get(position).hideBadge();
                ActionBar ab = ((MainActivity) getActivity()).getSupportActionBar();
                switch(position){
                    case 0 :
                        ab.setTitle("Latest Result");
                        fab.show();
                        break;
                    case 1 :
                        ab.setTitle("Past Result");
                        fab.show();
                        break;
                    case 2 :
                        ab.setTitle("Top Ranking");
                        fab.hide();
                        break;
                    case 3 :
                        ab.setTitle("My Bet");
                        fab.hide();
                        break;
                    case 4 :
                        ab.setTitle("Lala");
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(final int state) {

            }
        });

        navigationTabBar.post(new Runnable() {
            @Override
            public void run() {
                final View bgNavigationTabBar = view.findViewById(R.id.bg_ntb_horizontal);
                bgNavigationTabBar.getLayoutParams().height = (int) navigationTabBar.getBarHeight();
                bgNavigationTabBar.requestLayout();
            }
        });

//        navigationTabBar.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                for (int i = 0; i < navigationTabBar.getModels().size(); i++) {
//                    final NavigationTabBar.Model model = navigationTabBar.getModels().get(i);
//                    switch (i) {
//                        case 0:
//                            model.setBadgeTitle("NTB");
//                            break;
//                        case 1:
//                            model.setBadgeTitle("with");
//                            break;
//                        case 2:
//                            model.setBadgeTitle("title");
//                            break;
//                        case 3:
//                            model.setBadgeTitle("badge");
//                            break;
//                        case 4:
//                            model.setBadgeTitle("777");
//                            break;
//                        default:
//                            break;
//                    }
//                    navigationTabBar.postDelayed(new Runnable() {
//                        @Override
//                        public void run() {
//                            model.showBadge();
//                        }
//                    }, i * 100);
//                }
//            }
//        }, 500);


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "You clicked bet!", Toast.LENGTH_SHORT).show();
                navigationTabBar.setViewPager(viewPager, 3);
            }
        });


    }


    public MainFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MainFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MainFragment newInstance(String param1, String param2) {
        MainFragment fragment = new MainFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_main, container, false);


        initUI(v);
        return v;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    private PagerAdapter mPagerAdapter = new PagerAdapter() {
        @Override
        public int getCount() {
            return 5;
        }



        @Override
        public boolean isViewFromObject(final View view, final Object object) {
            return view.equals(object);
        }

        @Override
        public void destroyItem(final View container, final int position, final Object object) {
            ((ViewPager) container).removeView((View) object);
        }

        @Override
        public Object instantiateItem(final ViewGroup container, final int position) {
            View view = null;//= LayoutInflater.from(getActivity()).inflate(R.layout.viewpager_main, null, false);

            switch (position) {
                case 0:
                    view = LayoutInflater.from(getActivity()).inflate(R.layout.viewpager_latest, null, false);
                    break;

                case 1:
                    view = LayoutInflater.from(getActivity()).inflate(R.layout.viewpager_past, null, false);
                    widget = (MaterialCalendarView) view.findViewById(R.id.calendarView);
                    widget.setOnDateChangedListener(new OnDateSelectedListener() {
                        @Override
                        public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
                            //oneDayDecorator.setDate(date.getDate());
                            //widget.invalidateDecorators();
                            Toast.makeText(getActivity(), date.getDate().toString() + " is picked!", Toast.LENGTH_SHORT).show();
                        }
                    });

                    widget.addDecorators(new HighlightDaysDecorator(), new DisableDaysDecorator());
                    widget.setDateTextAppearance(R.color.accent);
                    widget.setArrowColor(R.color.accent);
                    Calendar calendar = Calendar.getInstance();
                    calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
                    widget.setMaximumDate(calendar.getTime());


                    break;

                case 2:
                    view = LayoutInflater.from(getActivity()).inflate(R.layout.viewpager_ranking, null, false);
                    ListView listView = (ListView) view.findViewById(R.id.list_view);


                    List<RankItem> list = new ArrayList<>();
                    list.add(new RankItem(1, "Poey Chin", "$100000", "http://www.chernyee.com/jokes/73.JPG"));
                    list.add(new RankItem(2, "Rose", "$80000", "http://www.chernyee.com/jokes/74.JPG"));
                    list.add(new RankItem(3, "Koookk", "$56640", "http://www.chernyee.com/jokes/75.JPG"));
                    list.add(new RankItem(4, "Pamagram", "$40010", "http://www.chernyee.com/jokes/76.JPG"));
                    list.add(new RankItem(5, "Dog Head", "$34001", "http://www.chernyee.com/jokes/77.JPG"));
                    list.add(new RankItem(6, "People Rich", "$20301", "http://www.chernyee.com/jokes/78.JPG"));
                    list.add(new RankItem(7, "Hostather", "$20010", "http://www.chernyee.com/jokes/79.JPG"));
                    list.add(new RankItem(8, "Kinky", "$10010", "http://www.chernyee.com/jokes/80.JPG"));
                    list.add(new RankItem(9, "Crab", "$6070", "http://www.chernyee.com/jokes/81.JPG"));
                    list.add(new RankItem(10, "Jonathan", "$1010", "http://www.chernyee.com/jokes/82.JPG"));


                    RankAdapter arrayAdapter = new RankAdapter(getActivity(), R.layout.ranking_view, list);
                    listView.setAdapter(arrayAdapter);


                    break;
                case 3:
                    view = LayoutInflater.from(getActivity()).inflate(R.layout.viewpager_bet, null, false);
                    Button btn = (Button) view.findViewById(R.id.btn_bet);
                    final SegmentTabLayout segmentTabLayout = (SegmentTabLayout) view.findViewById(R.id.tl_3);
                    final ListView lv = (ListView) view.findViewById(R.id.vp_2);

                    final List<String> list2 = new ArrayList<>();
                    list2.add("6/27/2016 -> 8533 -> WON -> $1000");
                    list2.add("6/25/2016 -> 5453 -> LOST -> -$1000");
                    list2.add("6/24/2016 -> 8864 -> WON -> $1000");
                    list2.add("6/23/2016 -> 2245 -> LOST -> -$7000");
                    list2.add("6/22/2016 -> 4345 -> LOST -> -$1000");
                    list2.add("6/21/2016 -> 3463 -> WON -> $10");
                    list2.add("6/17/2016 -> 8533 -> LOST -> -$1000");
                    list2.add("6/14/2016 -> 2355 -> WON -> $5000");
                    list2.add("6/13/2016 -> 4334 -> WON -> $1000");
                    list2.add("6/11/2016 -> 2332 -> WON -> $150");
                    list2.add("6/2/2016 -> 8533 -> WON -> $90");

                    final List<String> list3 = new ArrayList<>();
                    list3.add("5/23/2016 -> 2245 -> LOST -> -$888");
                    list3.add("3/22/2016 -> 4345 -> LOST -> -$888");
                    list3.add("2/21/2016 -> 3463 -> LOST -> -$888");
                    list3.add("1/17/2016 -> 8533 -> LOST -> -$888");
                    list3.add("1/14/2016 -> 2355 -> LOST -> -$888");
                    list3.add("1/13/2016 -> 4334 -> LOST -> -$888");
                    list3.add("1/11/2016 -> 2332 -> LOST -> -$888");
                    list3.add("1/2/2016 -> 8533 -> LOST -> -$888");

                    ArrayAdapter<String> ad = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, list2);
                    lv.setAdapter(ad);

                    segmentTabLayout.setTabData(new String[]{"Current Bet", "Past Bet"});
                    segmentTabLayout.setCurrentTab(0);
                    segmentTabLayout.setOnTabSelectListener(new OnTabSelectListener() {
                        @Override
                        public void onTabSelect(int position) {
                            if(position == 0){

                                ArrayAdapter<String> ad = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, list2);
                                lv.setAdapter(ad);

                            } else{

                                ArrayAdapter<String> ad2 = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, list3);
                                lv.setAdapter(ad2);

                            }

                        }

                        @Override
                        public void onTabReselect(int position) {

                        }
                    });



                    btn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            //display adding dialog
                            LayoutInflater inflater = getActivity().getLayoutInflater();
                            View dialoglayout = inflater.inflate(R.layout.betting_view, null);
                            final TextView addDateTextView = (TextView) dialoglayout.findViewById(R.id.text_add_date);
                            final MaterialEditText materialSmall = (MaterialEditText) dialoglayout.findViewById(R.id.edit_small);
                            final MaterialEditText materialBig = (MaterialEditText) dialoglayout.findViewById(R.id.edit_big);
                            final TextView totalTextView = (TextView) dialoglayout.findViewById(R.id.text_total);

                            materialSmall.addTextChangedListener(new TextWatcher() {
                                @Override
                                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                                }

                                @Override
                                public void onTextChanged(CharSequence s, int start, int before, int count) {
                                    if(s.length() > 0){
                                        int sum = Integer.parseInt(s.toString());
                                        if(materialBig.getText().toString().length() > 0){
                                            sum += Integer.parseInt(materialBig.getText().toString()) * 10;
                                        }
                                        totalTextView.setText("   " + sum);
                                    }

                                }

                                @Override
                                public void afterTextChanged(Editable s) {


                                }
                            });

                            materialBig.addTextChangedListener(new TextWatcher() {
                                @Override
                                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                                }

                                @Override
                                public void onTextChanged(CharSequence s, int start, int before, int count) {
                                    if(s.length() > 0){
                                        int sum = Integer.parseInt(s.toString()) * 10;
                                        if(materialBig.getText().toString().length() > 0){
                                            sum += Integer.parseInt(materialBig.getText().toString());
                                        }
                                        totalTextView.setText("   " + sum);
                                    }

                                }

                                @Override
                                public void afterTextChanged(Editable s) {

                                }
                            });



                            addDateTextView.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    LayoutInflater inflater = getActivity().getLayoutInflater();
                                    View dialoglayout = inflater.inflate(R.layout.calendar_view, null);
                                    MaterialCalendarView materialCalendarView = (MaterialCalendarView) dialoglayout.findViewById(R.id.calendarView);
                                    materialCalendarView.setOnDateChangedListener(new OnDateSelectedListener() {
                                        @Override
                                        public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
                                            //oneDayDecorator.setDate(date.getDate());
                                            dateInCalendarDialog = date.getMonth() + "/" + date.getDay() + "/" + date.getYear();

                                        }
                                    });

                                    materialCalendarView.addDecorators(new HighlightDaysDecorator(), new DisableDaysDecorator());
                                    materialCalendarView.setDateTextAppearance(R.color.accent);
                                    materialCalendarView.setArrowColor(R.color.accent);
                                    Calendar calendar = Calendar.getInstance();
                                    materialCalendarView.setMinimumDate(calendar.getTime());
                                    calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.DAY_OF_MONTH));
                                    materialCalendarView.setMaximumDate(calendar.getTime());



                                    final AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());
                                    alertDialog.setView(dialoglayout);
                                    alertDialog.setCancelable(false);
                                    alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int whichButton) {

                                            dateInCalendarDialog = "CLICK TO ADD";
                                            addDateTextView.setText(dateInCalendarDialog);
                                            dialog.cancel();
                                        }
                                    });

                                    alertDialog.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int whichButton) {
                                            // do something with it()

                                            Toast.makeText(getActivity(),
                                                    "Date is set!", Toast.LENGTH_LONG).show();
                                            addDateTextView.setText(dateInCalendarDialog);

                                            dialog.cancel();
                                        }
                                    });
                                    alertDialog.show();
                                }
                            });




                            final AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());
                            alertDialog.setView(dialoglayout);
                            alertDialog.setCancelable(false);

                            alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int whichButton) {

                                    dialog.cancel();
                                }
                            });

                            alertDialog.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int whichButton) {
                                    // do something with it()

                                    Toast.makeText(getActivity(),
                                             " is added!", Toast.LENGTH_LONG).show();



                                    dialog.cancel();
                                }
                            });

                            alertDialog.show();
                        }
                    });


                    break;
                case 4:
                    view = LayoutInflater.from(getActivity()).inflate(R.layout.viewpager_main, null, false);
                    final TextView txtPage2 = (TextView) view.findViewById(R.id.txt_vp_item_page);
                    txtPage2.setText(String.format("Page #%d", position));
                    break;
            }


            //     final TextView txtPage = (TextView) view.findViewById(R.id.txt_vp_item_page);
            //    txtPage.setText(String.format("Page #%d", position));
            container.addView(view);
            return view;
        }
    };






}
