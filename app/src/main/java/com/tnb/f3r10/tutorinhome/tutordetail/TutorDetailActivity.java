package com.tnb.f3r10.tutorinhome.tutordetail;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.tnb.f3r10.tutorinhome.R;
import com.tnb.f3r10.tutorinhome.TutorInHomeApplication;
import com.tnb.f3r10.tutorinhome.bookingtutor.ui.BookingTutorFragment;
import com.tnb.f3r10.tutorinhome.domain.Util;
import com.tnb.f3r10.tutorinhome.entities.Tutor;
import com.tnb.f3r10.tutorinhome.lib.base.ImageLoader;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.HashSet;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class TutorDetailActivity extends AppCompatActivity {

    @Bind(R.id.imgTutor)
    ImageView imgTutor;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.collapsing)
    CollapsingToolbarLayout collapsing;
    @Bind(R.id.appbar)
    AppBarLayout appbar;
    @Bind(R.id.ratingBar)
    RatingBar ratingBar;
    @Bind(R.id.txtScore)
    TextView txtScore;
    @Bind(R.id.imgEducation)
    ImageView imgEducation;
    @Bind(R.id.txtInstitution)
    TextView txtInstitution;
    @Bind(R.id.txtMajor)
    TextView txtMajor;
    @Bind(R.id.txtPrice)
    TextView txtPrice;
    @Bind(R.id.txtReview)
    TextView txtReview;
    @Bind(R.id.txtHourWork)
    TextView txtHourWork;
    @Bind(R.id.txtExperience)
    TextView txtExperience;
    @Bind(R.id.fab)
    FloatingActionButton fab;

    @Inject
    ImageLoader imageLoader;
    @Inject
    Util util;
    @Inject
    SharedPreferences sharedPreferences;
    TutorInHomeApplication app;
    Tutor tutor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutor_detail);
        ButterKnife.bind(this);
        app = (TutorInHomeApplication) getApplication();
        tutor = Parcels.unwrap(getIntent().getParcelableExtra(app.getTutorKey()));
        setToolbar();
        setUpInjection();
        setUpView();
    }

    private void setUpView() {
        if (tutor != null) {
            String institution = "Escuela Politecnica Nacional";
            String major = "Física Pura";
            collapsing.setTitle(tutor.getNickName());
            imageLoader.load(imgTutor, util.getAvatarUrl(tutor.getPicture_uri()));
            ratingBar.setRating(tutor.ratings.getNumber());
            txtScore.setText(Float.toString(tutor.ratings.getNumber()));
            if(tutor.getEducationTutor().size() > 0){
                institution = tutor.getEducationTutor().get(0).getInstitution();
                major = tutor.getEducationTutor().get(0).getMajor();
            }
            txtInstitution.setText(institution);
            txtMajor.setText(major);
            txtPrice.setText(Integer.toString(tutor.getPrice()) + " USD Hora");
            txtReview.setText(Integer.toString(tutor.getRatings().getCommentsNumber()) + " testimonios");
            txtHourWork.setText(Integer.toString(tutor.getHoursWorked()) + " horas clase");
            txtExperience.setText(tutor.getOverview());


        }
    }

    private void setUpInjection() {
        app.getTutorDetailComponent(this).inject(this);
    }

    private void setToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_detail, menu);
        return true;
    }

    @OnClick(R.id.fab)
    public void bookingClass() {
        Bundle args = new Bundle();
        BookingTutorFragment bookingTutorFragment = new BookingTutorFragment();
        args.putString(app.getTutorId(), tutor.getId());
        args.putStringArrayList(app.getBasicSubjectsKey(), new ArrayList<String>(tutor.getBasicSubjects()));
        args.putStringArrayList(app.getProfesionalSubjectsKey(), new ArrayList<String>(tutor.getProfessionalSubjects()));
        bookingTutorFragment.setArguments(args);
        bookingTutorFragment.show(getSupportFragmentManager(), getString(R.string.bookingtutor_message_title));
    }

}
