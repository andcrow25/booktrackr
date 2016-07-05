(function () {

    angular
        .module('booktrackrApp')
        .controller('HomeController', HomeController)
        .controller('SignupController', SignupController)
        .controller('LoginController', LoginController)
        .controller('BooksController', BooksController)
        .controller('NewBookController', NewBookController);

    HomeController.$inject = ['Book', '$log'];
    function HomeController(Book, $log) {
        var vm = this;

        Book.all().then(function (res) {
            $log.info(res.data);
            vm.books = res.data;
        }, function (err) {
            $log.info(err)
        })
    }

    SignupController.$inject = ['AuthService', '$log'];
    function SignupController(AuthService, $log) {
        var vm = this;

        vm.newUser = {};

        vm.signup = function () {
            AuthService.signup(vm.newUser)
                .then(function (res) {
                        $log.info('Signup succeeded', res);
                    },
                    function (err) {
                        $log.error('Signup failed ', err);
                    })

        }

    }

    LoginController.$inject = ['AuthService', '$log', '$location', 'TokenService'];
    function LoginController(AuthService, $log, $location, TokenService) {
        var vm = this;
        vm.credentials = {};

        vm.authenticate = function () {
            AuthService.login(vm.credentials)
                .then(
                    function (res) {
                        var jwt = res.headers('Authorization');
                        TokenService.setCurrentUserToken(jwt);
                        $location.path('/books');
                    },
                    function (err) {
                        $log.debug('Auth Failed');
                        // todo show failed auth message
                    })
        }


    }

    BooksController.$inject = ['Book', '$log'];
    function BooksController(Book, $log) {
        var vm = this;
        vm.books = [];

        Book.all().then(function (res) {
            $log.debug('Books response: ', res);
            vm.books = res.data;

        }, function (err) {
            $log.error('all books call failed', err);
        });
    }

    NewBookController.$inject = ['Book', '$location', '$log'];
    function NewBookController(Book, $location, $log) {
        var vm = this;
        vm.book = {};

        vm.save = function () {
            Book.create(vm.book).then(function (res) {
                $log.debug('created new book');
                $location.path('/books');
            }, function (err) {
                $log.debug('error creating new book', err);
            })
        }


    }


})();
