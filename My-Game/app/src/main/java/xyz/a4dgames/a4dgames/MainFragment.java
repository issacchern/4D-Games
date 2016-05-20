package xyz.a4dgames.a4dgames;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.gigamole.library.NavigationTabBar;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

import java.util.ArrayList;
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

    private OnFragmentInteractionListener mListener;

    private ViewPager viewPager;



    private void initUI(final View view) {
        final ViewPager viewPager = (ViewPager) view.findViewById(R.id.vp_horizontal_ntb);
        viewPager.setAdapter(new PagerAdapter() {
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

                switch(position){
                    case 0 :
                        view = LayoutInflater.from(getActivity()).inflate(R.layout.viewpager_latest, null, false);
                        break;

                    case 1 :
                        view = LayoutInflater.from(getActivity()).inflate(R.layout.viewpager_past, null, false);
                        MaterialCalendarView widget = (MaterialCalendarView) view.findViewById(R.id.calendarView);
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

                    case 2 :
                        view = LayoutInflater.from(getActivity()).inflate(R.layout.viewpager_ranking, null, false);
                        ListView listView = (ListView) view.findViewById(R.id.list_view);


                        List<RankItem> list = new ArrayList<>();
                        list.add(new RankItem(1, "Poey Chin", "$100000","http://www.chernyee.com/jokes/73.JPG"));
                        list.add(new RankItem(2, "Rose", "$80000","http://www.chernyee.com/jokes/74.JPG"));
                        list.add(new RankItem(3, "Koookk", "$56640","http://www.chernyee.com/jokes/75.JPG"));
                        list.add(new RankItem(4, "Pamagram", "$40010","http://www.chernyee.com/jokes/76.JPG"));
                        list.add(new RankItem(5, "Dog Head", "$34001","http://www.chernyee.com/jokes/77.JPG"));
                        list.add(new RankItem(6, "People Rich", "$20301","http://www.chernyee.com/jokes/78.JPG"));
                        list.add(new RankItem(7, "Hostather", "$20010","http://www.chernyee.com/jokes/79.JPG"));
                        list.add(new RankItem(8, "Kinky", "$10010","http://www.chernyee.com/jokes/80.JPG"));
                        list.add(new RankItem(9, "Crab", "$6070","http://www.chernyee.com/jokes/81.JPG"));
                        list.add(new RankItem(10, "Jonathan", "$1010","http://www.chernyee.com/jokes/82.JPG"));



                        RankAdapter arrayAdapter = new RankAdapter(getActivity(), R.layout.ranking_view, list);
                        listView.setAdapter(arrayAdapter);





                        break;
                    case 3 :
                        view = LayoutInflater.from(getActivity()).inflate(R.layout.viewpager_bet, null, false);
                        Spinner spinner = (Spinner) view.findViewById(R.id.spinner);
                        List<String> date = new ArrayList<String>();
                        date.add("5/19/2016 (THURSDAY)");
                        date.add("5/20/2016 (FRIDAY)");
                        ArrayAdapter<String> dateAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, date);
                        dateAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spinner.setAdapter(dateAdapter);



                        break;
                    case 4 :
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
        });

        viewPager.addOnPageChangeListener(mPageChangeListener);



        final String[] colors = getResources().getStringArray(R.array.default_preview);

        final NavigationTabBar navigationTabBar = (NavigationTabBar) view.findViewById(R.id.ntb_horizontal);
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

        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab);
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

    private ViewPager.OnPageChangeListener mPageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            ActionBar ab = ((MainActivity) getActivity()).getSupportActionBar();
            switch(position){
                case 0 :
                    ab.setTitle("Latest Result");
                    break;
                case 1 :
                    ab.setTitle("Past Result");
                    break;
                case 2 :
                    ab.setTitle("Top Ranking");
                    break;
                case 3 :
                    ab.setTitle("4D Fun");
                    break;
                case 4 :
                    ab.setTitle("Lala");
                    break;
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };




}
