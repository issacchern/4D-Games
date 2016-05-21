package xyz.a4dgames.a4dgames;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Handler;
import android.speech.RecognizerIntent;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.miguelcatalan.materialsearchview.MaterialSearchView;
import com.mikepenz.fontawesome_typeface_library.FontAwesome;
import com.mikepenz.google_material_typeface_library.GoogleMaterial;
import com.mikepenz.iconics.IconicsDrawable;
import com.mikepenz.itemanimators.AlphaCrossFadeAnimator;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.holder.BadgeStyle;
import com.mikepenz.materialdrawer.interfaces.OnCheckedChangeListener;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.ExpandableDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileSettingDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.SectionDrawerItem;
import com.mikepenz.materialdrawer.model.SwitchDrawerItem;
import com.mikepenz.materialdrawer.model.ToggleDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;
import com.mikepenz.materialdrawer.model.interfaces.Nameable;
import com.mikepenz.octicons_typeface_library.Octicons;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements MainFragment.OnFragmentInteractionListener{

    private static final int PROFILE_SETTING = 100000;
    private static final String TAG = "MainActivity" ;

    //save our header or result
    private AccountHeader headerResult = null;
    private Drawer result = null;
    private Drawer resultAppended = null;
    private boolean doubleBackToExitPressedOnce = false;
    private MaterialSearchView searchView ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Remove line to test RTL support
        //getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);

        // Handle Toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);




         // Create a few sample profile
        // NOTE you have to define the loader logic too. See the CustomApplication for more details
        final IProfile profile3 = new ProfileDrawerItem().withName("Issac Chua").withEmail("issac.awesomeness@gmail.com").withIcon(R.drawable.profile2).withIdentifier(100);
        // Create the AccountHeader
        headerResult = new AccountHeaderBuilder()
                .withActivity(this)
                .withTranslucentStatusBar(true)
                .withHeaderBackground(R.drawable.header)
                .withOnlyMainProfileImageVisible(true)
                .withCurrentProfileHiddenInList(true)
                .withSelectionListEnabled(false)
                .withOnAccountHeaderProfileImageListener(new AccountHeader.OnAccountHeaderProfileImageListener() {
                    @Override
                    public boolean onProfileImageClick(View view, IProfile profile, boolean current) {
                        Toast.makeText(MainActivity.this, "You click the profile!", Toast.LENGTH_SHORT).show();
                        return false;
                    }

                    @Override
                    public boolean onProfileImageLongClick(View view, IProfile profile, boolean current) {
                        headerResult.getHeaderBackgroundView().setImageResource(R.drawable.bg_round_rect);
                        return false;
                    }
                })
                .addProfiles(
                        profile3
                        //new ProfileSettingDrawerItem().withName("Profile Setting").withIcon(R.drawable.ic_c).withIdentifier(PROFILE_SETTING),
                        //new ProfileSettingDrawerItem().withName("Manage Account").withIcon(R.drawable.ic_dollar).withIdentifier(100001)
                )
                .withSavedInstance(savedInstanceState)
                .build();




        //Create the drawer
        result = new DrawerBuilder()
                .withActivity(this)
                .withToolbar(toolbar)
                .withHasStableIds(true)
                .withItemAnimator(new AlphaCrossFadeAnimator())
                .withAccountHeader(headerResult) //set the AccountHeader we created earlier for the header
                .addDrawerItems(
                        new PrimaryDrawerItem().withName("Home").withIcon(GoogleMaterial.Icon.gmd_home).withIdentifier(0),
                        new PrimaryDrawerItem().withName("Profile").withIcon(GoogleMaterial.Icon.gmd_account).withIdentifier(1),
                        new PrimaryDrawerItem().withName("Account").withIcon(FontAwesome.Icon.faw_navicon).withIdentifier(2),
                        new PrimaryDrawerItem().withName("Settings").withIcon(FontAwesome.Icon.faw_wheelchair).withIdentifier(3),
                        new PrimaryDrawerItem().withName("My Coins").withIcon(FontAwesome.Icon.faw_bitcoin).withIdentifier(4).withBadge("$100").withBadgeStyle(new BadgeStyle().withTextColor(Color.WHITE).withColorRes(R.color.md_red_700)),
                        new PrimaryDrawerItem().withName("About").withIcon(GoogleMaterial.Icon.gmd_account_box).withIdentifier(5).withSetSelected(false),
                        new PrimaryDrawerItem().withName("Logout").withIcon(GoogleMaterial.Icon.gmd_run).withIdentifier(7),
                        new SectionDrawerItem().withName("Others"),
                        new ExpandableDrawerItem().withName("Collapsable").withIcon(GoogleMaterial.Icon.gmd_collection_case_play).withIdentifier(19).withSelectable(false).withSubItems(
                                new SecondaryDrawerItem().withName("CollapsableItem").withLevel(2).withIcon(GoogleMaterial.Icon.gmd_8tracks).withIdentifier(2000),
                                new SecondaryDrawerItem().withName("CollapsableItem 2").withLevel(2).withIcon(GoogleMaterial.Icon.gmd_8tracks).withIdentifier(2001)
                        ),
                        new SecondaryDrawerItem().withName(R.string.drawer_item_open_source).withIcon(FontAwesome.Icon.faw_github).withIdentifier(20).withSelectable(false),
                        new SecondaryDrawerItem().withName(R.string.drawer_item_contact).withIcon(GoogleMaterial.Icon.gmd_format_color_fill).withIdentifier(21).withTag("Bullhorn")

                       ) // add the items we want to use with our Drawer
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {


                        if (drawerItem != null) {

                            Intent intent = null;
                            if (drawerItem.getIdentifier() == 5) {
                                intent = new Intent(MainActivity.this, AboutActivity.class);
                            } else if (drawerItem.getIdentifier() == 3) {
                                intent = new Intent(MainActivity.this, SettingsActivity.class);

                            } else if (drawerItem.getIdentifier() == 20) {
                  //              intent = new LibsBuilder()
                  //                      .withFields(R.string.class.getFields())
                  //                      .withActivityStyle(Libs.ActivityStyle.LIGHT_DARK_TOOLBAR)
                  //                      .intent(DrawerActivity.this);
                            }

                            if (intent != null) {
                                MainActivity.this.startActivity(intent);
                            }
                        }

                        return false;
                    }
                })
                .withSavedInstance(savedInstanceState)
                .build();





        resultAppended = new DrawerBuilder()
                .withActivity(this)
                .withFooter(R.layout.footer)
                .withDisplayBelowStatusBar(true)
                .withSavedInstance(savedInstanceState)
                .addDrawerItems(
                        new PrimaryDrawerItem().withName(R.string.drawer_item_custom).withIcon(FontAwesome.Icon.faw_eye),
                        new PrimaryDrawerItem().withName(R.string.drawer_item_home).withIcon(FontAwesome.Icon.faw_home),
                        new PrimaryDrawerItem().withName(R.string.drawer_item_free_play).withIcon(FontAwesome.Icon.faw_gamepad),
                        new SectionDrawerItem().withName(R.string.drawer_item_section_header),
                        new SecondaryDrawerItem().withName(R.string.drawer_item_settings).withIcon(FontAwesome.Icon.faw_cog),
                        new DividerDrawerItem(),
                        new SecondaryDrawerItem().withName(R.string.drawer_item_open_source).withIcon(FontAwesome.Icon.faw_github),
                        new SecondaryDrawerItem().withName(R.string.drawer_item_help).withIcon(FontAwesome.Icon.faw_question).withEnabled(false),
                        new SectionDrawerItem().withName(R.string.drawer_item_section_header),
                        new SecondaryDrawerItem().withName(R.string.drawer_item_contact).withIcon(FontAwesome.Icon.faw_bullhorn)
                )
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        if (drawerItem instanceof Nameable) {
                            Toast.makeText(MainActivity.this, ((Nameable) drawerItem).getName().getText(MainActivity.this), Toast.LENGTH_SHORT).show();
                        }
                        return false;
                    }
                })
                .withDrawerGravity(Gravity.END)
                .append(result);


        //only set the active selection or active profile if we do not recreate the activity
//        if (savedInstanceState == null) {
//            // set the selection to the item with the identifier 11
//            result.setSelection(21, false);
//
//            //set the active profile
//            headerResult.setActiveProfile(profile3);
//        }

//        result.updateBadge(4, new StringHolder(10 + ""));

        searchView = (MaterialSearchView) findViewById(R.id.search_view);
        searchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                //Do some magic
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                //Do some magic
                return false;
            }
        });

        searchView.setOnSearchViewListener(new MaterialSearchView.SearchViewListener() {
            @Override
            public void onSearchViewShown() {
                //Do some magic
            }

            @Override
            public void onSearchViewClosed() {
                //Do some magic
            }
        });

        searchView.setVoiceSearch(true);



        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        MainFragment fragment = MainFragment.newInstance("param1", "param2");
        transaction.replace(R.id.main_fragment, fragment);
        transaction.commit();



    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        //add the values which need to be saved from the drawer to the bundle
        outState = result.saveInstanceState(outState);
        //add the values which need to be saved from the accountHeader to the bundle
        outState = headerResult.saveInstanceState(outState);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onBackPressed() {
        //handle the back press :D close the drawer first and if the drawer is closed close the activity
        if (searchView.isSearchOpen())
            searchView.closeSearch();

        if (result != null && result.isDrawerOpen()) {
            result.closeDrawer();
        } else {
            if (doubleBackToExitPressedOnce) {
                super.onBackPressed();
                return;
            }
            this.doubleBackToExitPressedOnce =true;
            Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();
            new Handler().postDelayed(new Runnable() {

                @Override
                public void run() {
                    doubleBackToExitPressedOnce=false;
                }
            }, 2000);
        }


    }

    @Override
    public void onFragmentInteraction(Uri uri) {


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        MenuItem item = menu.findItem(R.id.action_search);
        searchView.setMenuItem(item);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == MaterialSearchView.REQUEST_VOICE && resultCode == RESULT_OK) {
            ArrayList<String> matches = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            if (matches != null && matches.size() > 0) {
                String searchWrd = matches.get(0);
                if (!TextUtils.isEmpty(searchWrd)) {
                    searchView.setQuery(searchWrd, false);
                }
            }

            return;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
