package com.qa.service;

import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class NoteServiceUnitTest {

    @InjectMocks
    private NoteService service;

    @Mock
    private NotesRepository repository;

    @Mock
    private ModelMapper mapper;

    private List<Note> noteList;

    private Note_testNote;

    private_long id = 1L;

    private_Note testNoteWithId;

    private_NoteDTO noteDTO(Note note){
        return this.mapper.map(note, NoteDTO.class);
    }

    @Before
    public void setUp(){
        this.noteList = new ArrayList<>();
        this.testNote = new Note("Shopping list", "Beer and even more beer");
        this.noteList.add(testNote);
        this.testNoteWithId = new Note(testNote.getTitle(), testNote.getDescription());
        this.testNoteWithId.setId(id);
        this.noteDTO = this.mapToDTO(testNoteWithId);
    }

    @Test
    public void getAllNotesTest(){
        when(repository.findAll()).thenReturn(this.noteList);
        when(this.mapper)
    }


}