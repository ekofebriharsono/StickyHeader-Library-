package com.maseko.root.stickyheaderusingitemdecoration;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.rv_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        final List<Person> people = PeopleRepo.getPeopleSorted();
        recyclerView.setAdapter(new PersonAdapter(people, R.layout.list_item));

        RecyclerSectionItemDecoration sectionItemDecoration =
                new RecyclerSectionItemDecoration(30,
                        true,
                        getSectionCallback(people));
        recyclerView.addItemDecoration(sectionItemDecoration);
    }

    private RecyclerSectionItemDecoration.SectionCallback getSectionCallback(final List<Person> people) {
        return new RecyclerSectionItemDecoration.SectionCallback() {
            @Override
            public boolean isSection(int position) {
                return position == 0
                        || people.get(position)
                        .getLastName()
                        .charAt(0) != people.get(position - 1)
                        .getLastName()
                        .charAt(0);
            }

            @Override
            public CharSequence getSectionHeader(int position) {
                return people.get(position)
                        .getLastName()
                        .subSequence(0,
                                1);
            }
        };
    }
}
